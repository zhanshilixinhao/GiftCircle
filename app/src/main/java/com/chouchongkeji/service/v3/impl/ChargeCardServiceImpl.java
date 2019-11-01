package com.chouchongkeji.service.v3.impl;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.dial.dao.v3.*;
import com.chouchongkeji.dial.pojo.iwant.wallet.ChargeOrder;
import com.chouchongkeji.dial.pojo.v3.*;
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
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.service.v3.ChargeCardService;
import com.chouchongkeji.service.v3.MemberCardService;
import com.chouchongkeji.service.v3.vo.EventVo;
import com.chouchongkeji.util.Constants;
import com.chouchongkeji.util.OrderHelper;
import com.github.pagehelper.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author linqin
 * @date 2019/10/29
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class ChargeCardServiceImpl implements ChargeCardService {

    @Autowired
    ServiceProperties serviceProperties;
    @Autowired
    private MemberCardMapper memberCardMapper;
    @Autowired
    private MemberEventMapper memberEventMapper;
    @Autowired
    private OrderHelper orderHelper;
    @Autowired
    private MemberChargeOrderMapper memberChargeOrderMapper;

    @Autowired
    private MemberChargeRecordMapper memberChargeRecordMapper;

    @Autowired
    private UserMemberCardMapper userMemberCardMapper;

    @Autowired
    private MemberCardService memberCardService;

    @Autowired
    private MemberExpenseRecordMapper  memberExpenseRecordMapper;

    @Autowired
    private StoreMemberChargeMapper storeMemberChargeMapper;

    /**
     * 礼遇圈会员卡充值规则
     *
     * @return
     * @author linqin
     * @date 2019/10/29
     */
    @Override
    public Response getChargeRuleList() {
        List<EventVo> eventVos = memberCardMapper.selectAll();
        return ResponseFactory.sucData(eventVos);
    }


    /**
     * 创建礼遇圈会员卡充值订单
     *
     * @param userId
     * @param eventId 活动id
     * @return
     * @author linqin
     * @date 2019/10/29
     */
    @Override
    public Response createdOrder(Integer userId, Integer client, Integer eventId, Integer payWay) {
        // 根据活动id查询充值金额赠送金额
        MemberEvent event = memberEventMapper.selectByPrimaryKey(eventId);
        if (event == null) {
            throw new ServiceException("请选择其他活动");
        }
        Long orderNo = orderHelper.genOrderNo(client, 6);
        // 创建订单
        MemberChargeOrder order = new MemberChargeOrder();
        order.setOrderNo(orderNo);
        order.setMembershipCardId(0);
        order.setUserId(userId);
        order.setRechargeMoney(event.getRechargeMoney());
        order.setSendMoney(event.getSendMoney());
        order.setStatus(Constants.CHARGE_ORDER_STATUS.NO_PAY);
        order.setPayWay(payWay.byteValue());
        int insert = memberChargeOrderMapper.insert(order);
        if (insert < 1) {
            throw new ServiceException("创建订单失败");
        }
        // 创建订单参数
        PayVO payVO = assemblePayOrder(orderNo, payWay, event.getRechargeMoney());
        //根据不同的支付方式创建不同的支付参数
        String info = null;
        if (payWay == Constants.PAY_TYPE.ALI) {
            // 支付宝
            info = AliPayServiceV2.createOrderInfo(payVO);
            info = RSAProvider.encrypt(info, KeyUtil.PRIVATE_KEY);
        } else {
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
        payResultVo.setOrderNo(order.getOrderNo());
        return ResponseFactory.sucData(payResultVo);
    }


    /**
     * 构造支付宝|微信需要的支付参数
     *
     * @param order 用户订单
     * @return
     */
    private PayVO assemblePayOrder(Long order, Integer payWay, BigDecimal amount) {
        PayVO vo = new PayVO();
        vo.setBody(Constants.PAY_BODY);
        vo.setSubject(Constants.PAY_SUBJECT_ORDER);
        vo.setOrderNo(order);
        //支付宝
        if (payWay == Constants.PAY_TYPE.ALI) {
            vo.setUrl(serviceProperties.getHost() + "noauth/pay/v3/member_order/ali");
        } else if (payWay == Constants.PAY_TYPE.WX) {//微信
            vo.setUrl(serviceProperties.getHost() + "noauth/pay/v3/member_order/wx");
        }
        vo.setPrice(amount);
        return vo;
    }


    /**
     * 礼遇圈会员卡支付成功后的业务逻辑
     *
     * @return
     */
    @Override
    public void successPay(Long orderNo) {
        //更新订单支付状态
        MemberChargeOrder chargeOrder = memberChargeOrderMapper.selectByPrimaryKey(orderNo);
        chargeOrder.setStatus(Constants.CHARGE_ORDER_STATUS.PAY);
        int count = memberChargeOrderMapper.updateByPrimaryKeySelective(chargeOrder);
        if (count == 0) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "更新失败");
        }
        //更新余额
        BigDecimal amount = BigDecimalUtil.add(chargeOrder.getRechargeMoney().doubleValue(),chargeOrder.getSendMoney().doubleValue());
        updateBalance(chargeOrder.getUserId(),amount,new BigDecimal("0"));
        // 添加充值记录
        MemberChargeRecord record = new MemberChargeRecord();
        record.setMembershipCardId(0);
        record.setUserId(chargeOrder.getUserId());
        record.setRechargeMoney(chargeOrder.getRechargeMoney());
        record.setSendMoney(chargeOrder.getSendMoney());
        record.setType((byte)1);
        record.setStoreId(0);
        record.setExplain("余额充值");
        int insert = memberChargeRecordMapper.insert(record);
        if (insert == 0) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "添加充值记录失败");
        }
        //添加会员卡使用详情记录
        BigDecimal total = BigDecimalUtil.add(chargeOrder.getRechargeMoney().doubleValue(),chargeOrder.getSendMoney().doubleValue());
        // 营业额比例（赠送金额/总金额（充值金额+赠送金额））
        BigDecimal sca = BigDecimalUtil.div(chargeOrder.getSendMoney().doubleValue(),total.doubleValue());
        addStoreMountDetail(chargeOrder.getUserId(),0,0,chargeOrder.getRechargeMoney(),chargeOrder.getSendMoney(),
               new BigDecimal("0"),(byte)1,"余额充值", total,sca.floatValue());
    }




    /**
     * 添加会员卡使用详情记录
     *
     */
    @Override
    public void addStoreMountDetail(Integer userId,Integer merchantId,Integer storeId,BigDecimal rec,BigDecimal send,BigDecimal expense,
                                    Byte type,String explain,BigDecimal total,Float scale){
        StoreMemberCharge member = new StoreMemberCharge();
        member.setUserId(userId);
        member.setMerchantId(merchantId);
        member.setStoreId(storeId);
        member.setRechargeMoney(rec);
        member.setSendMoney(send);
        member.setExpenseMoney(expense);
        member.setType(type);
        member.setExplain(explain);
        member.setTotalMoney(total);
        member.setScale(scale);
        int insert = storeMemberChargeMapper.insert(member);
        if (insert == 0) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "添加会员卡使用记录失败");
        }
    }

    /**
     * 更新余额
     * @param userId 用户id
     * @param amount 充值金额+赠送金额
     * @param consume 消费金额
     */
    @Override
    public void updateBalance(Integer userId, BigDecimal amount, BigDecimal consume) {
        UserMemberCard card = userMemberCardMapper.selectByCardIdUserId(userId, 0);
        if (card == null) {
            int i = memberCardService.addMemberShipCard(userId, amount, amount, consume);
            if (i == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新余额失败");
            }
        }else {
            BigDecimal add = BigDecimalUtil.add(card.getBalance().doubleValue(), amount.doubleValue());
            card.setBalance(BigDecimalUtil.sub(add.doubleValue(), consume.doubleValue()));
            card.setTotalAmount(BigDecimalUtil.add(card.getTotalAmount().doubleValue(), amount.doubleValue()));
            card.setConsumeAmount(BigDecimalUtil.add(card.getConsumeAmount().doubleValue(), consume.doubleValue()));
            int i = userMemberCardMapper.updateByPrimaryKeySelective(card);
            if (i == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新余额失败！");
            }
        }
    }

    /**
     * 添加会员卡消费记录
     * @param userId 用户id
     * @param amount 消费金额
     * @param targetId 目标id
     * @param explain 消费说明
     */
    @Override
    public void addExpenseRecord(Integer userId,BigDecimal amount,String targetId,String explain){
        MemberExpenseRecord record = new MemberExpenseRecord();
        record.setMembershipCardId(0);
        record.setUserId(userId);
        record.setExpenseMoney(amount);
        record.setType((byte)1);
        record.setStoreId(0);
        record.setTargetId(targetId);
        record.setExplain(explain);
        int insert = memberExpenseRecordMapper.insert(record);
        if (insert == 0) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "添加消费记录失败！");
        }
    }

}
