package com.chouchongkeji.mvc.controller.user.info;

import com.chouchongkeji.goexplore.pay.alipay_v2.ALiPayV2Vo;
import com.chouchongkeji.goexplore.pay.weixin.common.Util;
import com.chouchongkeji.service.user.info.AppPaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author linqin
 * @date 2018/6/8
 */
@RestController
@RequestMapping("noauth/pay")
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
     * 商品订单支付宝回调
     * @param aLiPayV2Vo
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/21
     */
    @PostMapping("item_order/ali")
    public String itemOrderAliPay(ALiPayV2Vo aLiPayV2Vo,Map parameterMap){
        /** 调用基础方法 **/
        return this.itemBaseAliPay(aLiPayV2Vo,parameterMap,(byte)2);
    }

    /**
     * 商品订单支付宝回调基础方法
     * @param aLiPayV2Vo
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/21
     */
    public String itemBaseAliPay(ALiPayV2Vo aLiPayV2Vo,Map parameterMap,Byte orderType){
        /** 调用基础方法 **/
        return appPaymentInfoService.itemOrderAli(aLiPayV2Vo,parameterMap,orderType);
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

    /*-----------------------------------------------支付宝支付回调结束---------------------------------------------------*/

    /*-----------------------------------------------微信支付回调开始---------------------------------------------------*/

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


    /**
     * 商品订单微信支付回调
     * @param request
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/21
     */
    @PostMapping("item_order/wx")
    public String itemOrderWXPay(HttpServletRequest request) throws Exception {
        /** 调用基础方法 **/
        return this.itemBaseWXPay(request, (byte) 2);
    }

    /**
     * 微信支付回调基础方法
     * @param request
     * @param orderType
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    public String itemBaseWXPay( HttpServletRequest request, Byte orderType)throws Exception{
        String xml = Util.inputStreamToString(request.getInputStream());
        return appPaymentInfoService.ItemOrderWXPay(xml,orderType);

    }



}
