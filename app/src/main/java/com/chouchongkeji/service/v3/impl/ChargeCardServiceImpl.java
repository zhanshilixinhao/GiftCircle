package com.chouchongkeji.service.v3.impl;

import com.chouchongkeji.dial.dao.v3.MemberCardMapper;
import com.chouchongkeji.dial.dao.v3.MemberChargeOrderMapper;
import com.chouchongkeji.dial.dao.v3.MemberEventMapper;
import com.chouchongkeji.dial.pojo.iwant.wallet.ChargeOrder;
import com.chouchongkeji.dial.pojo.v3.MemberChargeOrder;
import com.chouchongkeji.dial.pojo.v3.MemberEvent;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.pay.PayVO;
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.service.v3.ChargeCardService;
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
@Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)
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
     * @param eventId     活动id
     * @return
     * @author linqin
     * @date 2019/10/29
     */
    @Override
    public Response createdOrder(Integer userId, Integer client, Integer eventId,Integer payWay) {
         // 根据活动id查询充值金额赠送金额
        MemberEvent event = memberEventMapper.selectByPrimaryKey(eventId);
        if (event == null){
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
        if (insert < 1){
            throw new ServiceException("创建订单失败");
        }
        // 创建订单参数
        PayVO payVO = assemblePayOrder(orderNo, payWay, event.getRechargeMoney());



        return null;
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
        if (payWay == Constants.PAY_TYPE.ALI){
            vo.setUrl(serviceProperties.getHost()+"noauth/pay/charge_order/ali");
        } else if (payWay == Constants.PAY_TYPE.WX){//微信
            vo.setUrl(serviceProperties.getHost()+"noauth/pay/charge_order/wx");
        }
        vo.setPrice(amount);
        return vo;
    }

}
