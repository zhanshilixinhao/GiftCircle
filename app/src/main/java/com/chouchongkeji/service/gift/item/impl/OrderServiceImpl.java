package com.chouchongkeji.service.gift.item.impl;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.dial.dao.gift.item.ItemOrderDetailMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemOrderMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemSkuMapper;
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
import com.chouchongkeji.goexplore.utils.BigDecimalUtil;
import com.chouchongkeji.goexplore.utils.RSAProvider;
import com.chouchongkeji.dial.pojo.gift.item.ItemOrder;
import com.chouchongkeji.dial.pojo.gift.item.ItemOrderDetail;
import com.chouchongkeji.dial.pojo.gift.item.ItemSku;
import com.chouchongkeji.service.gift.item.OrderService;
import com.chouchongkeji.service.gift.item.vo.OrderVo;
import com.chouchongkeji.util.Constants;
import com.chouchongkeji.util.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author linqin
 * @date 2018/6/20
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemOrderMapper itemOrderMapper;

    @Autowired
    private ItemOrderDetailMapper itemOrderDetailMapper;

    @Autowired
    private ItemSkuMapper itemSkuMapper;

    @Autowired
    private OrderHelper orderHelper;

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
    @Override
    public Response createOrder(Integer userId, Integer client, HashSet<OrderVo> skus,Integer payWay) {
        Long orderNo = orderHelper.genOrderNo(client, 2);
        List<ItemOrderDetail> list = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal("0");
        int quantity = 0;
        for (OrderVo order : skus) {
            //判断sku是否存在，并且判断库存是否充足
            //1.判断sku是否存在
            ItemSku itemSku = itemSkuMapper.selectBySkuId(order.getSkuId());
            if (itemSku == null) {
                throw new ServiceException(ErrorCode.ERROR.getCode(),"该商品不存在" );
            }
            //2.判断库存是否充足
            int count = itemSku.getStock();
            if (count < order.getQuantity()) {
                throw new ServiceException(ErrorCode.ERROR.getCode(),"库存不足" );

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
            itemSku.setStock(count-quantity);
            itemSkuMapper.updateByPrimaryKeySelective(itemSku);
        }
        //创建订单
        ItemOrder itemOrder = new ItemOrder();
        itemOrder.setUserId(userId);
        itemOrder.setOrderNo(orderNo);
        itemOrder.setQuantity(quantity);
        itemOrder.setTotalPrice(totalPrice);
        itemOrder.setStatus((byte)1);
        int insert = itemOrderMapper.insert(itemOrder);
        if (insert<1){
            throw new ServiceException(ErrorCode.ERROR.getCode(),"创建订单失败" );
        }
        //批量加入
        itemOrderDetailMapper.batchInsert(list);
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
                throw new ServiceException(ErrorCode.ERROR.getCode(),"创建微信订单失败，" + dto.getMessage());
            }
            info = RSAProvider.encrypt(JSON.toJSONString(dto), KeyUtil.PRIVATE_KEY);
        }
        if (info == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(),"支付参数创建失败!" );
        }
        // 请求支付成功事务
        PayResultVo payResultVo = new PayResultVo();
        payResultVo.setParams(info);
        payResultVo.setType(payWay);
        payResultVo.setOrderNo(itemOrder.getOrderNo());
        return ResponseFactory.sucData(payResultVo);

    }

    private ItemOrderDetail orderDetail(Integer userId, ItemSku sku, Long orderNo,
                                       BigDecimal totalPrice, Integer quantity) {
        ItemOrderDetail itemOrderDetail = new ItemOrderDetail();
        itemOrderDetail.setUserId(userId);
        itemOrderDetail.setItemId(sku.getItemId());
        itemOrderDetail.setSkuId(sku.getId());
        itemOrderDetail.setOrderNo(orderNo);
        itemOrderDetail.setTitle(sku.getTitle());
        itemOrderDetail.setCover(sku.getCover());
        itemOrderDetail.setPrice(sku.getPrice());
        itemOrderDetail.setQuantity(quantity);
        itemOrderDetail.setTotalPrice(totalPrice);
        itemOrderDetail.setStatus(sku.getStatus());
        return itemOrderDetail;
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
        vo.setUrl("yuu");
        vo.setPrice(order.getTotalPrice());
        return vo;
    }

    /*------------------------------------创建订单结束--------------------------------------------------*/

    /*------------------------------------订单支付开始--------------------------------------------------*/

    /**
     * 订单支付
     * @param userId   用户id
     * @param orderNo  订单号
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    @Override
    public Response orderPay(Integer userId, Long orderNo) {
        //取出订单信息
//        ItemOrder itemOrder = itemOrderMapper.selectByUserId
        return null;
    }



}
