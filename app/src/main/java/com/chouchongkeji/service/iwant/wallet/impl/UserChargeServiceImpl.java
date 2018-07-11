package com.chouchongkeji.service.iwant.wallet.impl;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.dial.dao.iwant.wallet.ChargeOrderMapper;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.pay.KeyUtil;
import com.chouchongkeji.goexplore.pay.PayResultVo;
import com.chouchongkeji.goexplore.pay.PayVO;
import com.chouchongkeji.goexplore.pay.alipay_v2.AliPayServiceV2;
import com.chouchongkeji.goexplore.pay.weixin.service.WXPayDto;
import com.chouchongkeji.goexplore.pay.weixin.service.WXPayService;
import com.chouchongkeji.goexplore.utils.RSAProvider;
import com.chouchongkeji.dial.pojo.iwant.wallet.ChargeOrder;
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.service.iwant.wallet.UserChargeService;
import com.chouchongkeji.util.Constants;
import com.chouchongkeji.util.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2018/6/6
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class UserChargeServiceImpl implements UserChargeService {

    @Autowired
    private ChargeOrderMapper chargeOrderMapper;

    @Autowired
    private OrderHelper orderHelper;

    @Autowired
    ServiceProperties serviceProperties;
    /**
     * 创建充值订单
     *
     * @param userId 用户id
     * @param amount 充值金额
     * @param payWay 支付方式  微信24656，支付宝78990
     * @return
     * @author linqin
     * @date 2018/6/6
     */
    @Override
    public Response chargeOrder(Integer userId, Integer client, BigDecimal amount,
                                Integer payWay) {

        //创建充值订单
        ChargeOrder order = new ChargeOrder();
        order.setUserId(userId);
        order.setAmount(amount);
        order.setOrderNo(orderHelper.genOrderNo(client, 1));
        order.setStatus(Constants.CHARGE_ORDER_STATUS.NO_PAY);
        //插入一条数据
        int count = chargeOrderMapper.insert(order);
        if (count == 0) {
            return ResponseFactory.err("充值订单创建失败");
        }
        //创建订单参数
        PayVO payVO = assemblePayOrder(order, payWay);
        //根据不同的支付方式创建不同的支付参数
        String info = null;
        // 如果是支付宝，构造支付宝参数并签名
        if (payWay == Constants.PAY_TYPE.ALI) {
            info = AliPayServiceV2.createOrderInfo(payVO);
//            System.out.println(info);
//            mingwen = info;
            info = RSAProvider.encrypt(info, KeyUtil.PRIVATE_KEY);
        } else { // 微信支付
            WXPayDto dto = WXPayService.service(payVO).createPrePay();
            if (dto.getCode() != 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(),"创建微信订单失败，" + dto.getMessage());
            }
//            mingwen = JSON.toJSONString(dto);
            info = RSAProvider.encrypt(JSON.toJSONString(dto), KeyUtil.PRIVATE_KEY);
        }
        if (info == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(),"支付参数创建失败!" );
        }
        // 请求支付成功事务
        PayResultVo payResultVo = new PayResultVo();
        payResultVo.setParams(info);
        payResultVo.setType(payWay);
        payResultVo.setOrderNo(order.getOrderNo());
//        payResultVo.setParams1(mingwen);
        return ResponseFactory.sucData(payResultVo);

//        return ResponseFactory.sucData(count, "充值订单创建成功");
    }

    /**
     * 构造支付宝|微信需要的支付参数
     *
     * @param order 用户订单
     * @return
     */
    private PayVO assemblePayOrder(ChargeOrder order, Integer payWay) {
        PayVO vo = new PayVO();
        vo.setBody(Constants.PAY_BODY);
        vo.setSubject(Constants.PAY_SUBJECT_ORDER);
        vo.setOrderNo(order.getOrderNo());
        //支付宝
        if (payWay == Constants.PAY_TYPE.ALI){
            vo.setUrl(serviceProperties.getHost()+"noauth/pay/charge_order/ali");
        } else if (payWay == Constants.PAY_TYPE.WX){//微信
            vo.setUrl(serviceProperties.getHost()+"noauth/pay/charge_order/wx");
        }
        vo.setPrice(order.getAmount());
        return vo;
    }


}
