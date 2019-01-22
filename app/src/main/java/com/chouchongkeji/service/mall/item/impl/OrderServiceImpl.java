package com.chouchongkeji.service.mall.item.impl;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.dial.dao.gift.item.*;
import com.chouchongkeji.dial.pojo.gift.item.*;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.pay.KeyUtil;
import com.chouchongkeji.goexplore.pay.PayResultVo;
import com.chouchongkeji.goexplore.pay.PayVO;
import com.chouchongkeji.goexplore.pay.alipay_v2.AliPayServiceV2;
import com.chouchongkeji.goexplore.pay.weixin.service.WXPayDto;
import com.chouchongkeji.goexplore.pay.weixin.service.WXPayService;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.goexplore.utils.BigDecimalUtil;
import com.chouchongkeji.goexplore.utils.RSAProvider;
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.service.backpack.base.BpService;
import com.chouchongkeji.service.iwant.wallet.WalletService;
import com.chouchongkeji.service.mall.item.OrderService;
import com.chouchongkeji.service.mall.item.vo.OrderListVo;
import com.chouchongkeji.service.mall.item.vo.OrderVo;
import com.chouchongkeji.service.mall.item.vo.SkuListVo;
import com.chouchongkeji.service.mall.item.vo.SkuValueVo;
import com.chouchongkeji.service.user.info.AppPaymentInfoService;
import com.chouchongkeji.util.Constants;
import com.chouchongkeji.util.OrderHelper;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author linqin
 * @date 2018/6/20
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class OrderServiceImpl implements OrderService {
    private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private ItemOrderMapper itemOrderMapper;

    @Autowired
    private ItemOrderDetailMapper itemOrderDetailMapper;

    @Autowired
    private ItemSkuMapper itemSkuMapper;

    @Autowired
    private OrderHelper orderHelper;

    @Autowired
    private WalletService walletService;

    @Autowired
    private AppPaymentInfoService appPaymentInfoService;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private BpService bpService;

    @Autowired
    private ServiceProperties serviceProperties;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrderCollectMapper orderCollectMapper;

    /**
     * 按时取消订单
     */
    @Scheduled(fixedRate = 600000)
    public void timeTask() {
        //取出所有未支付的订单
        List<ItemOrder> itemOrders = itemOrderMapper.selectAll();
        if (CollectionUtils.isEmpty(itemOrders)) {
            log.info("没有需要支付的订单");
            return;
        }
        for (ItemOrder order : itemOrders) {
            Long orderNo = order.getOrderNo();
            //更新订单状态为已取消
            itemOrderMapper.updateStatusByOrder(orderNo, Constants.ORDER_STATUS.CANCELED);
            //更新详细订单状态为已取消
            List<ItemOrderDetail> list = itemOrderDetailMapper.selectByOrderNo(orderNo);
            for (ItemOrderDetail orderDetail : list) {
                //更新详细订单的状态
                itemOrderDetailMapper.updateStatus(orderNo, Constants.ORDER_STATUS.CANCELED);
                //更新sku的库存(把商品退回到itemSku的库存中)
                int count = itemSkuMapper.updateStockBySkuId(orderDetail.getSkuId(), orderDetail.getQuantity());
                if (count < 1) {
                    log.info("库存更新失败");
                }
                log.info("库存更新成功");
            }
        }
        log.info("订单取消成功");
    }


    /**
     * 创建商品订单
     *
     * @param userId
     * @param client
     * @param skus
     * @return
     * @author linqin
     * @date 2018/6/20
     */
//    @Override
//    public Response createOrder(Integer userId, Integer client, HashSet<OrderVo> skus, Integer payWay, Byte isShoppingCart) {
//        List<ItemOrderDetail> list = new ArrayList<>();
//        HashMap<Integer, List<OrderVo>> hashMap = new HashMap<>();
//        for (OrderVo orderVo : skus) {
//            ItemSku sku = itemSkuMapper.selectBySkuId(orderVo.getSkuId());
//            if (sku == null) {
//                throw new ServiceException(ErrorCode.ERROR.getCode(), "该商品不存在");
//            }
//            Item item = itemMapper.selectByPrimaryKey(sku.getItemId());
//            List<OrderVo> orderVos = hashMap.get(item.getAdminId());
//            if (orderVos == null) {
//                orderVos = new ArrayList<>();
//                hashMap.put(item.getAdminId(), orderVos);
//            }
//            orderVos.add(orderVo);
//        }
//        List<ItemOrder> itemOrders = new ArrayList<>();
//        // 遍历店铺
//        hashMap.forEach((key,value) -> {
//            ItemOrder itemOrder = create(value, client, userId, isShoppingCart);
//            itemOrders.add(itemOrder);
//        });
//        BigDecimal totalPrice = new BigDecimal("0");
//        OrderCollect orderCollect = new OrderCollect();
//        Long orderNo =  + orderHelper.genOrderNo(9, 2);
//        for (ItemOrder itemOrder : itemOrders) {
//            totalPrice=BigDecimalUtil.add(totalPrice.doubleValue(),itemOrder.getTotalPrice().doubleValue());
//            // 添加订单号集合信息
//            orderCollect.setOrderNo(itemOrder.getOrderNo());
//            orderCollect.sethOrderNo(orderNo);
//            orderCollectMapper.insert(orderCollect);
//        }
//        //余额支付
//        if (payWay == Constants.PAY_TYPE.yue) {
//            //扣减余额，更新余额
//            int response = yuePay(userId,totalPrice, orderCollect.getId(), orderNo);
//            if (response < 1) {
//                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新余额,扣减余额失败");
//            }
//            //更新销量和详细订单状
//            int i = updateStatusSales(orderNo);
//            if (i < 1) {
//                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新销量和详细订单状态失败");
//            }
//            //物品添加到背包
//            int add = bpService.addFromItemOrder(list);
//            if (add < 1) {
//                throw new ServiceException(ErrorCode.ERROR.getCode(), "");
//            }
//            //保存支付信息
//            appPaymentInfoService.doYuePaySuccess(orderNo, userId, orderCollect.getCreated(), Constants.ORDER_TYPE.ITEM,
//                    0, totalPrice);
//            return ResponseFactory.sucMsg("支付成功");
//        }
//        // 创建订单参数
////        return ResponseFactory.sucData(createOrderParameter(totalPrice, payWay));
//        return null;
//    }


    //创建订单
    public Response createOrder(Integer userId, Integer client, HashSet<OrderVo> skus, Integer payWay, Byte isShoppingCart) {
        Long orderNo = orderHelper.genOrderNo(client, 2);
        List<ItemOrderDetail> list = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal("0");
        int quantity = 0;
        for (OrderVo order : skus) {
            //判断sku是否存在
            ItemSku itemSku = itemSkuMapper.selectBySkuId(order.getSkuId());
            if (itemSku == null) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "该商品不存在");
            }
            //判断状态是否正常
            //1.判断商品状态
            Integer itemId = itemSku.getItemId();
            Item item = itemMapper.selectByPrimaryKey(itemId);
            if (item.getStatus() != Constants.ITEM.NORMAL) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "该商品已下架或者删除");
            }
            //2.判断sku状态
            if (itemSku.getStatus() != Constants.ITEM.NORMAL) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "该商品已下架或者删除");
            }
            //.判断库存是否充足
            int count = itemSku.getStock();
            if (count < order.getQuantity()) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "库存不足");
            }
            //计算商品价格
            BigDecimal price = itemSku.getPrice().multiply(new BigDecimal(order.getQuantity()));
            //初始化订单
            list.add(orderDetail(userId, itemSku, orderNo, price, order.getQuantity()));
            //计算订单总价
            totalPrice = BigDecimalUtil.add(price.doubleValue(), totalPrice.doubleValue());
            //计算总数量
            quantity = quantity + order.getQuantity();
            //扣除库存
            itemSku.setStock(count - order.getQuantity());
            itemSkuMapper.updateByPrimaryKeySelective(itemSku);
            // 如果是购物车购买则减少购物车物品数量
            if (isShoppingCart == Constants.ISSHOPPINGCART.YES) {
                Cart cart = cartMapper.selectBySkuIAndUserId(userId, order.getSkuId());
                if (cart == null) {
                    return ResponseFactory.err("购物车中不存在该商品");
                }
                cart.setQuantity(cart.getQuantity() - order.getQuantity());
                cartMapper.updateByPrimaryKeySelective(cart);
                if (cart.getQuantity() <= 0) {
                    cartMapper.deleteAllByUserIdAndskuId(userId, order.getSkuId());
                }
            }
        }
        //创建订单
        ItemOrder itemOrder = new ItemOrder();
        itemOrder.setUserId(userId);
        itemOrder.setOrderNo(orderNo);
        itemOrder.setQuantity(quantity);
        itemOrder.setTotalPrice(totalPrice);
        itemOrder.setStatus((byte) Constants.ORDER_STATUS.NO_PAY);
        int insert = itemOrderMapper.insert(itemOrder);
        if (insert < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "创建订单失败");
        }
        // 批量加入到详细订单
        itemOrderDetailMapper.batchInsert(list);
        //余额支付
        if (payWay == Constants.PAY_TYPE.yue) {
            //扣减余额，更新余额
            int response = yuePay(userId, itemOrder.getTotalPrice(), itemOrder.getId(), orderNo);
            if (response < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新余额,扣减余额失败");
            }
            //更新销量和详细订单状
            int i = updateStatusSales(orderNo);
            if (i < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新销量和详细订单状态失败");
            }
            //物品添加到背包
            int add = bpService.addFromItemOrder(list);
            if (add < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "");
            }
            //保存支付信息
            appPaymentInfoService.doYuePaySuccess(orderNo, userId, itemOrder.getCreated(), Constants.ORDER_TYPE.ITEM,
                    0, itemOrder.getTotalPrice());
            return ResponseFactory.sucMsg("支付成功");
        }
        // 创建订单参数
        return ResponseFactory.sucData(createOrderParameter(itemOrder, payWay));
    }

    private ItemOrder create(List<OrderVo> skus,Integer client,Integer userId,Byte isShoppingCart){
        Long orderNo = orderHelper.genOrderNo(client, 2);
        List<ItemOrderDetail> list = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal("0");
        int quantity = 0;
        for (OrderVo order : skus) {
            //判断sku是否存在
            ItemSku itemSku = itemSkuMapper.selectBySkuId(order.getSkuId());
            if (itemSku == null) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "该商品不存在");
            }
            //判断状态是否正常
            //1.判断商品状态
            Integer itemId = itemSku.getItemId();
            Item item = itemMapper.selectByPrimaryKey(itemId);
            if (item.getStatus() != Constants.ITEM.NORMAL) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "该商品已下架或者删除");
            }
            //2.判断sku状态
            if (itemSku.getStatus() != Constants.ITEM.NORMAL) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "该商品已下架或者删除");
            }
            //.判断库存是否充足
            int count = itemSku.getStock();
            if (count < order.getQuantity()) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "库存不足");
            }
            //计算商品价格
            BigDecimal price = itemSku.getPrice().multiply(new BigDecimal(order.getQuantity()));
            //初始化订单
            list.add(orderDetail(userId, itemSku, orderNo, price, order.getQuantity()));
            //计算订单总价
            totalPrice = BigDecimalUtil.add(price.doubleValue(), totalPrice.doubleValue());
            //计算总数量
            quantity = quantity + order.getQuantity();
            //扣除库存
            itemSku.setStock(count - order.getQuantity());
            itemSkuMapper.updateByPrimaryKeySelective(itemSku);
            // 如果是购物车购买则减少购物车物品数量
            if (isShoppingCart == Constants.ISSHOPPINGCART.YES) {
                Cart cart = cartMapper.selectBySkuIAndUserId(userId, order.getSkuId());
                if (cart == null) {
                    throw new ServiceException(ErrorCode.ERROR.getCode(),"购物车中不存在该商品") ;
                }
                cart.setQuantity(cart.getQuantity() - order.getQuantity());
                cartMapper.updateByPrimaryKeySelective(cart);
                if (cart.getQuantity() <= 0) {
                    cartMapper.deleteAllByUserIdAndskuId(userId, order.getSkuId());
                }
            }
        }
        //创建订单
        ItemOrder itemOrder = new ItemOrder();
        itemOrder.setUserId(userId);
        itemOrder.setOrderNo(orderNo);
        itemOrder.setQuantity(quantity);
        itemOrder.setTotalPrice(totalPrice);
        itemOrder.setStatus((byte) Constants.ORDER_STATUS.NO_PAY);
        int insert = itemOrderMapper.insert(itemOrder);
        if (insert < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "创建订单失败");
        }
        // 批量加入到详细订单
        itemOrderDetailMapper.batchInsert(list);
        return itemOrder;
    }

    private ItemOrderDetail orderDetail(Integer userId, ItemSku sku, Long orderNo,
                                        BigDecimal totalPrice, Integer quantity) {
        ItemOrderDetail itemOrderDetail = new ItemOrderDetail();
        itemOrderDetail.setUserId(userId);
        itemOrderDetail.setItemId(sku.getItemId());
        itemOrderDetail.setSkuId(sku.getId());
        itemOrderDetail.setOrderNo(orderNo);
        // 保存规格信息
        SkuListVo skuListVo = itemSkuMapper.selectDetailBySkuId(sku.getId());
        itemOrderDetail.setTitle(sku.getTitle() + genTitle(skuListVo));
        itemOrderDetail.setCover(sku.getCover());
        itemOrderDetail.setPrice(sku.getPrice());
        itemOrderDetail.setQuantity(quantity);
        itemOrderDetail.setTotalPrice(totalPrice);
        itemOrderDetail.setStatus((byte) Constants.ORDER_STATUS.NO_PAY);

        itemOrderDetail.setSp(JSON.toJSONString(skuListVo.getValues()));
        return itemOrderDetail;
    }

    /**
     * 取出规格信息
     *
     * @param skuListVo
     * @return
     */
    public String genTitle(SkuListVo skuListVo) {
        if (skuListVo == null) return "";
        StringBuilder sb = new StringBuilder();
        for (SkuValueVo value : skuListVo.getValues()) {
            sb.append(" ").append(value.getValue());
        }
        return sb.toString();
    }

    /**
     * 构造支付宝|微信需要的支付参数
     *
     * @param order 用户订单
     * @return
     */
    private PayVO assemblePayOrder(ItemOrder order, Integer payWay) {
        PayVO vo = new PayVO();
        vo.setBody(Constants.PAY_BODY);
        vo.setSubject(Constants.PAY_ITEM_ORDER);
        vo.setOrderNo(order.getOrderNo());
        //支付宝
        if (payWay == Constants.PAY_TYPE.ALI) {
            vo.setUrl(serviceProperties.getHost() + "noauth/pay/item_order/ali");
        } else if (payWay == Constants.PAY_TYPE.WX) {//微信
            vo.setUrl(serviceProperties.getHost() + "noauth/pay/item_order/wx");
        }
        vo.setPrice(order.getTotalPrice());
        return vo;
    }

    /**
     * 余额支付
     *
     * @param userId
     * @param totalPrice
     * @param itemOrderId
     * @param orderNo
     * @return
     * @author linqin
     * @date 2018/7/5
     */
    public int yuePay(Integer userId, BigDecimal totalPrice, Integer itemOrderId, Long orderNo) {
        //扣减余额，更新余额，钱包记录
        int count = walletService.updateBalance(userId, totalPrice, Constants.WALLET_RECORD.BUY_ITEM, itemOrderId);
        if (count < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "支付失败,请选择其他支付方式");
        }
        //更新订单状态
        int i = itemOrderMapper.updateStatusByOrder(orderNo, Constants.ORDER_STATUS.PAID);
        if (i < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "更新状态失败");
        }
        return 1;
    }


    /**
     * 更新销量和详细订单状态
     *
     * @param orderNo
     * @author linqin
     * @date 2018/7/5
     */
    public int updateStatusSales(Long orderNo) {
        //取出订单详细信息
        List<ItemOrderDetail> itemOrderDetail = itemOrderDetailMapper.selectByOrderNo(orderNo);
        for (ItemOrderDetail itemDetail : itemOrderDetail) {
            //更新详细订单状态
            itemDetail.setStatus((byte) Constants.ORDER_BASE_STATUS.PAID);
            int update = itemOrderDetailMapper.updateByPrimaryKeySelective(itemDetail);
            if (update == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新状态失败");
            }
            //更新ItemSku销量
            Integer skuId = itemDetail.getSkuId();
            Integer quantity = itemDetail.getQuantity();
            Integer sales = itemSkuMapper.updateSalesBySkuId(skuId, quantity);
            if (sales == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新销量失败");
            }
            //更新Item销量
            Integer itemId = itemDetail.getItemId();
            Integer ItemSales = itemMapper.updateSalesByItemId(itemId, quantity);
            if (ItemSales == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新销量失败");
            }
        }
        return 1;
    }


    /*------------------------------------订单支付--------------------------------------------------*/

    /**
     * 订单支付
     *
     * @param userId  用户id
     * @param orderNo 订单号
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    @Override
    public Response orderPay(Integer userId, Long orderNo, Integer payWay) {
        //取出订单信息
        ItemOrder itemOrder = itemOrderMapper.selectByUserIdOrderNo(userId, orderNo);
        //校验订单是否存在
        if (itemOrder == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "该订单不存在");
        }
        //只能支付未支付的订单（校验订单是否支付过）
        if (itemOrder.getStatus() != Constants.ORDER_STATUS.NO_PAY) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "订单已经支付过");
        }
        //余额支付
        if (payWay == Constants.PAY_TYPE.yue) {
            //更新余额，扣减余额
            int response = yuePay(userId, itemOrder.getTotalPrice(), itemOrder.getId(), orderNo);
            if (response < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新余额,扣减余额失败");
            }
            //更新销量和详细订单状态
            int sales = updateStatusSales(orderNo);
            if (sales < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新余额,扣减余额失败");
            }
            //物品添加到背包
            List<ItemOrderDetail> list = itemOrderDetailMapper.selectByUserIdAndOrderNo(userId, orderNo);
            int add = bpService.addFromItemOrder(list);
            if (add < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "");
            }
            //保存支付信息
            appPaymentInfoService.doYuePaySuccess(orderNo, userId, itemOrder.getCreated(), Constants.ORDER_TYPE.ITEM,
                    0, itemOrder.getTotalPrice());
            return ResponseFactory.sucMsg("支付成功");
        }
        return ResponseFactory.sucData(createOrderParameter(itemOrder, payWay));
    }

    /**
     * 创建订单参数
     *
     * @param itemOrder
     * @param payWay
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    private PayResultVo createOrderParameter(ItemOrder itemOrder, Integer payWay) {
        //创建订单参数
        PayVO payVO = assemblePayOrder(itemOrder, payWay);
        //根据不同的支付方式创建不同的支付参数
        String info = null;
        // 如果是支付宝，构造支付宝参数并签名
        if (payWay == Constants.PAY_TYPE.ALI) {
            info = AliPayServiceV2.createOrderInfo(payVO);
            info = RSAProvider.encrypt(info, KeyUtil.PRIVATE_KEY);
        } else { // 微信支付
            WXPayDto dto = WXPayService.service(payVO).createPrePay();
            if (dto.getCode() != 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "创建微信订单失败，" + dto.getMessage());
            }
            info = RSAProvider.encrypt(JSON.toJSONString(dto), KeyUtil.PRIVATE_KEY);
        }
        if (info == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "支付参数创建失败!");
        }
        // 请求支付成功事务
        PayResultVo payResultVo = new PayResultVo();
        payResultVo.setParams(info);
        payResultVo.setType(payWay);
        payResultVo.setOrderNo(itemOrder.getOrderNo());
        return payResultVo;

    }

    /*------------------------------------订单取消--------------------------------------------------*/


    /**
     * 订单取消
     *
     * @param userId  用户id
     * @param orderNo 订单号
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    @Override
    public Response cancelOrder(Integer userId, Long orderNo) {
        //取出订单信息
        ItemOrder itemOrder = itemOrderMapper.selectByUserIdOrderNo(userId, orderNo);
        if (itemOrder == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "订单不存在，无权操作");
        }
        //只能支付未支付的订单（校验订单是否支付过）
        if (itemOrder.getStatus() != Constants.ORDER_STATUS.NO_PAY) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "订单已经支付过");
        }
        //退还库存
        //取出订单详细信息
        List<ItemOrderDetail> itemOrderDetail = itemOrderDetailMapper.selectByUserIdAndOrderNo(userId, orderNo);
        if (itemOrderDetail == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "订单不存在");
        }
        for (ItemOrderDetail orderDetail : itemOrderDetail) {
            Integer skuId = orderDetail.getSkuId(); //取出商品skuId
            Integer quantity = orderDetail.getQuantity();//取出购买商品数量
            ItemSku itemSku = itemSkuMapper.selectBySkuId(skuId);
            if (itemSku == null) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "商品不存在或已下架");
            }
            itemSku.setStock(itemSku.getStock() + quantity);//退还库存
            int stock = itemSkuMapper.updateByPrimaryKeySelective(itemSku);//更新库存
            if (stock < 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新库存失败");
            }
            orderDetail.setStatus((byte) Constants.ORDER_STATUS.CANCELED);
            itemOrderDetailMapper.updateByPrimaryKeySelective(orderDetail);
        }
        //更新订单状态
        itemOrder.setStatus((byte) Constants.ORDER_STATUS.CANCELED);
        int i = itemOrderMapper.updateByPrimaryKeySelective(itemOrder);
        if (i < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "取消失败，请刷新重试");
        }
        return ResponseFactory.sucMsg("订单取消成功");
    }

    /*------------------------------------订单列表--------------------------------------------------*/

    /**
     * 订单列表
     *
     * @param userId    用户Id
     * @param pageQuery 分页
     * @param status    状态 1-未完成（未付款），2-已完成（已付款）
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    @Override
    public Response orderList(Integer userId, PageQuery pageQuery, Integer status) {
        //分页
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        //根据用户id和状态查询所有订单
        List<OrderListVo> itemOrders = itemOrderMapper.selectDetailByUserId(userId, status);
        if (itemOrders == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "还没有订单哦");
        }
        return ResponseFactory.sucData(itemOrders);
    }
}
