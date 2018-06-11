package com.chouchongkeji.controller.user;

import com.chouchongkeji.goexplore.pay.alipay_v2.ALiPayV2Vo;
import com.chouchongkeji.goexplore.pay.weixin.common.Util;
import com.chouchongkeji.service.user.AppPaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author linqin
 * @date 2018/6/8
 */
@RestController
public class PayNotifyInterfaceController {

    @Autowired
    private AppPaymentInfoService appPaymentInfoService;

    /**
     * 充值订单支付宝支付回调
     * @param alipayVo
     * @param request
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/8
     */
    @PostMapping("charge_order/ali")
    public String chargeOrderAliPay(ALiPayV2Vo alipayVo, HttpServletRequest request)throws Exception{
        /** 调用基础方法 **/
        return this.baseAliPay(alipayVo, request.getParameterMap(),(byte)1);
    }

    /**
     * 支付宝支付回调基础方法
     * @param aLiPayV2Vo
     * @param parameterMap
     * @return
     * @author linqin
     * @date 2018/6/8
     */
    public String baseAliPay(ALiPayV2Vo aLiPayV2Vo, Map parameterMap,Byte orderType){
        //收到回调
        return appPaymentInfoService.orderAliPay(aLiPayV2Vo,parameterMap,orderType);
    }


    /**
     * 充值订单微信支付回调
     * @param request
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/8
     */
    @PostMapping("charge_order/wx")
    public String orderWeixinPay(HttpServletRequest request) throws Exception {
        /** 调用基础方法 **/
        return this.baseWeixinPay(request, (byte) 1);
    }

    /**
     * 微信支付回调基础方法
     * @param request
     * @param orderType
     * @return
     * @author linqin
     * @date 2018/6/8
     */
    public String baseWeixinPay( HttpServletRequest request, Byte orderType)throws Exception{
        String xml = Util.inputStreamToString(request.getInputStream());

            return appPaymentInfoService.orderWXPay(xml,orderType);

    }


}
