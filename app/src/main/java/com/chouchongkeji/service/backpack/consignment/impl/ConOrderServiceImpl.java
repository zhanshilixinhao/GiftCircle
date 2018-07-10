package com.chouchongkeji.service.backpack.consignment.impl;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.dial.dao.backpack.consignment.ConsignmentMapper;
import com.chouchongkeji.dial.dao.backpack.consignment.ConsignmentOrderMapper;
import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.pojo.backpack.consignment.Consignment;
import com.chouchongkeji.dial.pojo.backpack.consignment.ConsignmentOrder;
import com.chouchongkeji.dial.pojo.user.AppUser;
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
import com.chouchongkeji.goexplore.utils.RSAProvider;
import com.chouchongkeji.service.backpack.base.BpService;
import com.chouchongkeji.service.backpack.consignment.ConOrderService;
import com.chouchongkeji.service.iwant.wallet.WalletService;
import com.chouchongkeji.service.mall.item.impl.OrderServiceImpl;
import com.chouchongkeji.service.message.MessageService;
import com.chouchongkeji.service.user.info.AppPaymentInfoService;
import com.chouchongkeji.util.Constants;
import com.chouchongkeji.util.OrderHelper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author linqin
 * @date 2018/7/3
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class ConOrderServiceImpl implements ConOrderService {

    private  static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderHelper orderHelper;

    @Autowired
    private ConsignmentMapper consignmentMapper;

    @Autowired
    private ConsignmentOrderMapper consignmentOrderMapper;

    @Autowired
    private WalletService walletService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private AppPaymentInfoService appPaymentInfoService;

    @Autowired
    private BpService bpService;



    /**
     * 寄售台订单超时取消订单
     */
    @Scheduled(fixedRate = 600000)
    public void conTimeTask(){
        log.info("开始执行取消寄售台订单");
        List<ConsignmentOrder> list = consignmentOrderMapper.selectAllByStatus();
        if (CollectionUtils.isEmpty(list)){
            log.info( "没有需要支付的订单");
            return;
        }
        for ( ConsignmentOrder order:list) {
            //更新订单状态为已取消
            int count = consignmentOrderMapper.updateStatus(order.getOrderNo(), Constants.ORDER_STATUS.CANCELED);
            if (count < 1) {
                log.info("订单状态更新失败");
                continue;
            }
            //更新寄售台数量
            int i = consignmentMapper.updateQuantity(order.getConsignmentId(), order.getQuantity());
            if (i < 1) {
                log.info("数量更新失败");
            }
        }
    }



    /**
     * 寄售台订单创建
     *
     * @param userId        用户id
     * @param client
     * @param consignmentId 寄售台Id
     * @param payWay        支付方式  微信 24656  ，支付宝78990   ，余额 98001
     * @return
     * @author linqin
     * @date 2018/7/3
     */
    @Override
    public Response createOrder(Integer userId, Integer client, Integer consignmentId, Integer payWay) {
        //订单号
        Long orderNo = orderHelper.genOrderNo(client, 5);
        //查询商品
        Consignment consignment = consignmentMapper.selectByPrimaryKey(consignmentId);
        if (consignment == null) {
            return ResponseFactory.err("寄售台中不存在改商品");
        }
        //商品数量大于0才能购买
        Integer quantity = consignment.getQuantity();
        if (quantity < 1) {
            return ResponseFactory.err("商品数量不足");
        }
        //商品状态为1（上架）才能购买
        if (consignment.getStatus() != Constants.CONSIGNMENT_ITEM.UP) {
            return ResponseFactory.err("上架的商品才能购买");
        }

        //创建订单
        ConsignmentOrder consignmentOrder = new ConsignmentOrder();
        consignmentOrder.setOrderNo(orderNo);
        consignmentOrder.setUserId(userId);
        consignmentOrder.setSellUserId(consignment.getUserId());
        consignmentOrder.setConsignmentId(consignmentId);
        consignmentOrder.setQuantity(1);
        consignmentOrder.setPrice(consignment.getPrice());
        consignmentOrder.setStatus((byte) Constants.ORDER_BASE_STATUS.NO_PAY);//1未支付，2-已支付
        int insert = consignmentOrderMapper.insert(consignmentOrder);
        if (insert < 1) {
            return ResponseFactory.err("订单创建失败");
        }
        //更新寄售台
        consignment.setQuantity(quantity - 1);
        consignment.setStatus(Constants.CONSIGNMENT_ITEM.NO_PAY);
        consignmentMapper.updateByPrimaryKeySelective(consignment);
        //余额支付（直接扣减余额,跟新余额）
        if (payWay == Constants.PAY_TYPE.yue) {
            int response = yuePay(userId, consignment.getPrice(), orderNo, consignmentOrder.getId(), consignmentId);
            if (response < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(),"更新余额,扣减余额失败");
            }
            //系统消息
            ConsignmentOrder conOrder = consignmentOrderMapper.selectByUserIdOrder(userId, orderNo);
            if (conOrder == null) {
                return ResponseFactory.err("该订单不存在");
            }
            AppUser nickName = appUserMapper.selectByPrimaryKey(conOrder.getUserId());
            int i = messageService.addMessage(Constants.APP_MESSAGE_TYPE.CONSIGNMENT,
                    "您交易的物品被" + nickName.getNickname()
                    + "购买，快去看看吧", null, consignmentOrder.getId(), userId);
            //增加卖家余额金额
            int wall = walletService.updateBalance(conOrder.getSellUserId(), conOrder.getPrice(), Constants.WALLET_RECORD.CONSIGNMENT_ITEM,
                    conOrder.getId());
            if (wall < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(),"卖家余额增加失败");
            }
            //物品添加到背包
            int add = bpService.addFromConsignmengOrder(conOrder);
            if (add < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(),"");
            }
            //保存支付信息
            appPaymentInfoService.doYuePaySuccess(orderNo,userId,conOrder.getCreated(),Constants.ORDER_TYPE.CON_ITEM,
                    conOrder.getSellUserId(),conOrder.getPrice());
            return ResponseFactory.suc("支付成功", i);
        }
        //创建订单参数
        return ResponseFactory.sucData(createOrderParameter(consignmentOrder, payWay));
    }

    /**
     * 创建订单参数
     *
     * @param consignmentOrder
     * @param payWay
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    private PayResultVo createOrderParameter(ConsignmentOrder consignmentOrder, Integer payWay) {
        //创建订单参数
        PayVO payVO = assemblePayOrder(consignmentOrder, payWay);
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
        payResultVo.setOrderNo(consignmentOrder.getOrderNo());
        return payResultVo;
    }

    /**
     * 构造支付宝|微信需要的支付参数
     *
     * @param order 用户订单
     * @return
     */
    private PayVO assemblePayOrder(ConsignmentOrder order, Integer payWay) {
        PayVO vo = new PayVO();
        vo.setBody(Constants.PAY_BODY);
        vo.setSubject(Constants.PAY_CON_ORDER);
        vo.setOrderNo(order.getOrderNo());
        //支付宝
        if (payWay == Constants.PAY_TYPE.ALI) {
            vo.setUrl("noauth/pay/con_order/ali");
        } else if (payWay == Constants.PAY_TYPE.WX) {//微信
            vo.setUrl("noauth/pay/con_order/wx");
        }
        vo.setPrice(order.getPrice());
        return vo;
    }
    /*------------------------------------创建订单--------------------------------------------------*/

    /*------------------------------------订单支付--------------------------------------------------*/


    /**
     * 寄售台订单支付
     *
     * @param userId  用户id
     * @param orderNo 订单号
     * @param payWay  支付方式  微信 24656  ，支付宝78990   ，余额 98001
     * @return
     * @author linqin
     * @date 2018/7/3
     */
    @Override
    public Response orderPay(Integer userId, Long orderNo, Integer payWay) {
        //取出订单信息
        ConsignmentOrder consignmentOrder = consignmentOrderMapper.selectByUserIdOrder(userId, orderNo);
        if (consignmentOrder == null) {
            return ResponseFactory.err("该订单不存在");
        }
        //只能支付未支付过的订单
        if (consignmentOrder.getStatus() != Constants.ORDER_BASE_STATUS.NO_PAY) {
            return ResponseFactory.err("只能支付未支付过的订单");
        }
        //余额支付（直接扣减余额,跟新余额）
        if (payWay == Constants.PAY_TYPE.yue) {
            int response = yuePay(userId, consignmentOrder.getPrice(), orderNo, consignmentOrder.getId(),
                    consignmentOrder.getConsignmentId());
            if (response < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(),"更新余额,扣减余额失败");
            }
            //系统消息
            AppUser nickName = appUserMapper.selectByPrimaryKey(consignmentOrder.getId());
            int i = messageService.addMessage(Constants.APP_MESSAGE_TYPE.CONSIGNMENT, "您交易的物品被" + nickName.getNickname()
                    + "购买，快去看看吧", null, consignmentOrder.getId(), userId);
            //增加卖家金额
            int wall = walletService.updateBalance(consignmentOrder.getSellUserId(), consignmentOrder.getPrice(),
                    Constants.WALLET_RECORD.CONSIGNMENT_ITEM, consignmentOrder.getId());
            if (wall < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(),"卖家余额增加失败");
            }
            //物品添加到背包
            int add = bpService.addFromConsignmengOrder(consignmentOrder);
            if (add < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(),"");
            }
            //保存支付信息
            appPaymentInfoService.doYuePaySuccess(orderNo,userId,consignmentOrder.getCreated(),
                    Constants.ORDER_TYPE.CON_ITEM,consignmentOrder.getSellUserId(),consignmentOrder.getPrice());
            return ResponseFactory.suc("支付成功", i);
        }
        return ResponseFactory.sucData(createOrderParameter(consignmentOrder, payWay));
    }


    /**
     * 余额支付
     *
     * @param userId
     * @param price
     * @param orderNo
     * @param targetId
     * @return
     * @author linqin
     *  @date 2018/7/4
     */
    public int yuePay(Integer userId, BigDecimal price, Long orderNo, Integer targetId, Integer consignmentId) {
        //更新余额，扣减余额
        int count = walletService.updateBalance(userId, price, Constants.WALLET_RECORD.CON_BUY_ITEM, targetId);
        if (count < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(),"支付失败,请选择其他支付方式");
        }
        //更新寄售台商品状态
        Consignment consignment = consignmentMapper.selectByPrimaryKey(consignmentId);
        if (consignment == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(),"寄售台中不存在改商品");
        }
        consignment.setStatus(Constants.CONSIGNMENT_ITEM.PAY);
        consignmentMapper.updateByPrimaryKeySelective(consignment);
        //更新订单状态
        ConsignmentOrder conOrder = consignmentOrderMapper.selectByUserIdOrder(userId, orderNo);
        if (conOrder == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(),"该订单不存在");
        }
        conOrder.setStatus((byte) Constants.ORDER_BASE_STATUS.PAID);
        consignmentOrderMapper.updateByPrimaryKeySelective(conOrder);
        return 1;
    }


}
