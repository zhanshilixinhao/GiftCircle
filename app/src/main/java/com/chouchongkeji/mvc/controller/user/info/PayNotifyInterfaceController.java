package com.chouchongkeji.mvc.controller.user.info;

import com.chouchongkeji.goexplore.pay.alipay_v2.ALiPayV2Vo;
import com.chouchongkeji.goexplore.pay.weixin.common.Util;
import com.chouchongkeji.service.user.info.AppPaymentInfoService;
import com.chouchongkeji.util.Constants;
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
     *
     * @param alipayVo
     * @param request
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/8
     */
    @RequestMapping("charge_order/ali")
    public String chargeOrderAliPay(ALiPayV2Vo alipayVo, HttpServletRequest request) throws Exception {
        /** 调用基础方法 **/
        return this.baseAliPay(alipayVo, request.getParameterMap(), Constants.ORDER_TYPE.CHARGE);
    }

    /**
     * 充值支付宝支付回调基础方法
     *
     * @param aLiPayV2Vo
     * @param parameterMap
     * @param orderType    订单类型 1-充值订单，2-商品订单，3-寄售台商品订单
     * @return
     * @author linqin
     * @date 2018/6/8
     */
    public String baseAliPay(ALiPayV2Vo aLiPayV2Vo, Map parameterMap, Byte orderType) {
        //收到回调
        return appPaymentInfoService.orderAliPay(aLiPayV2Vo, parameterMap, orderType);
    }

    /*-----------------------------------------------商品支付宝支付回调---------------------------------------------------*/

    /**
     * 商品订单支付宝回调
     *
     * @param aLiPayV2Vo
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/21
     */
    @RequestMapping("item_order/ali")
    public String itemOrderAliPay(ALiPayV2Vo aLiPayV2Vo, HttpServletRequest request) {
        /** 调用基础方法 **/
        return this.itemBaseAliPay(aLiPayV2Vo, request.getParameterMap(), Constants.ORDER_TYPE.ITEM);
    }

    /**
     * 商品订单支付宝回调基础方法
     *
     * @param aLiPayV2Vo
     * @param orderType  订单类型 1-充值订单，2-商品订单，3-寄售台商品订单
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/21
     */
    public String itemBaseAliPay(ALiPayV2Vo aLiPayV2Vo, Map parameterMap, Byte orderType) {
        /** 调用基础方法 **/
        return appPaymentInfoService.itemOrderAli(aLiPayV2Vo, parameterMap, orderType);
    }

    /*-----------------------------------------------寄售台商品支付宝支付回调---------------------------------------------------*/

    /**
     * 寄售台商品订单支付宝回调
     *
     * @param aLiPayV2Vo
     * @param parameterMap
     * @return
     * @author linqin
     * @date 2018/7/5
     */
    @RequestMapping("con_order/ali")
    public String conOrderAliPay(ALiPayV2Vo aLiPayV2Vo, HttpServletRequest request) {
        /** 调用基础方法 **/
        return this.conBaseAliPay(aLiPayV2Vo, request.getParameterMap(), Constants.ORDER_TYPE.CON_ITEM);
    }


    /**
     * 寄售台支付宝回调基本方法
     *
     * @param aLiPayV2Vo
     * @param parameterMap
     * @param orderType    订单类型 1-充值订单，2-商品订单，3-寄售台商品订单
     * @return
     * @author linqin
     * @date 2018/7/5
     */
    public String conBaseAliPay(ALiPayV2Vo aLiPayV2Vo, Map parameterMap, Byte orderType) {
        /** 调用基础方法 **/
        return appPaymentInfoService.conOrderAli(aLiPayV2Vo, parameterMap, orderType);
    }

    /*-----------------------------------------------礼遇圈会员卡宝支付回调---------------------------------------------------*/


    /**
     * 礼遇圈会员卡宝支付回调
     *
     * @param alipayVo
     * @param request
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/8
     */
    @RequestMapping("v3/member_order/ali")
    public String memberOrderAliPay(ALiPayV2Vo alipayVo, HttpServletRequest request) throws Exception {
        /** 调用基础方法 **/
        return this.memberAliPay(alipayVo, request.getParameterMap(), Constants.ORDER_TYPE.MEMBER_CHARGE);
    }

    /**
     * 礼遇圈会员卡宝支付回调基础方法
     *
     * @param aLiPayV2Vo
     * @param parameterMap
     * @param orderType    订单类型 1-充值订单，2-商品订单，3-寄售台商品订单 4- 会员卡充值订单
     * @return
     * @author linqin
     * @date 2018/6/8
     */
    public String memberAliPay(ALiPayV2Vo aLiPayV2Vo, Map parameterMap, Byte orderType) {
        //收到回调
        return appPaymentInfoService.memberAliPay(aLiPayV2Vo, parameterMap, orderType);
    }


    /*-----------------------------------------------支付宝支付回调结束---------------------------------------------------*/

    /*-----------------------------------------------微信支付回调开始---------------------------------------------------*/

    /**
     * 充值订单微信支付回调
     *
     * @param request
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/8
     */
    @RequestMapping("charge_order/wx")
    public String orderWeixinPay(HttpServletRequest request) throws Exception {
        /** 调用基础方法 **/
        return this.baseWeixinPay(request, Constants.ORDER_TYPE.CHARGE);
    }

    /**
     * 充值微信支付回调基础方法
     *
     * @param request
     * @param orderType 订单类型 1-充值订单，2-商品订单，3-寄售台商品订单
     * @return
     * @author linqin
     * @date 2018/6/8
     */
    public String baseWeixinPay(HttpServletRequest request, Byte orderType) throws Exception {
        String xml = Util.inputStreamToString(request.getInputStream());
        return appPaymentInfoService.orderWXPay(xml, orderType);

    }
    /*-----------------------------------------------商品微信支付回调---------------------------------------------------*/

    /**
     * 商品订单微信支付回调
     *
     * @param request
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/21
     */
    @RequestMapping("item_order/wx")
    public String itemOrderWXPay(HttpServletRequest request) throws Exception {
        /** 调用基础方法 **/
        return this.itemBaseWXPay(request, Constants.ORDER_TYPE.ITEM);
    }

    /**
     * 商品微信支付回调基础方法
     *
     * @param request
     * @param orderType 订单类型 1-充值订单，2-商品订单，3-寄售台商品订单
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    public String itemBaseWXPay(HttpServletRequest request, Byte orderType) throws Exception {
        String xml = Util.inputStreamToString(request.getInputStream());
        return appPaymentInfoService.ItemOrderWXPay(xml, orderType);

    }
    /*----------------------------------------------寄售台商品支付宝支付回调-------------------------------------------------*/

    /**
     * 寄售台微信支付回调
     *
     * @param request
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/7/5
     */
    @RequestMapping("con_order/wx")
    public String conOrderWXPay(HttpServletRequest request) throws Exception {
        /** 调用基础方法 **/
        return this.conBaseWXPay(request, Constants.ORDER_TYPE.CON_ITEM);
    }

    /**
     * 寄售台微信支付回调基础方法
     *
     * @param request
     * @param orderType 订单类型 1-充值订单，2-商品订单，3-寄售台商品订单 4- 会员卡充值订单
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/7/5
     */
    public String conBaseWXPay(HttpServletRequest request, Byte orderType) throws Exception {
        String xml = Util.inputStreamToString(request.getInputStream());
        return appPaymentInfoService.conOrderWXPay(xml, orderType);
    }

    /*-----------------------------------------------礼遇圈会员卡充值微信支付回调---------------------------------------------------*/


    /**
     * 礼遇圈会员卡充值订单微信支付回调
     *
     * @param request
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/8
     */
    @RequestMapping("v3/member_order/wx")
    public String orderMemberPay(HttpServletRequest request) throws Exception {
        /** 调用基础方法 **/
        return this.baseMemberPay(request, Constants.ORDER_TYPE.CHARGE);
    }

    /**
     * 礼遇圈会员卡充值微信支付回调基础方法
     *
     * @param request
     * @param orderType 订单类型 1-充值订单，2-商品订单，3-寄售台商品订单 4- 会员卡充值订单
     * @return
     * @author linqin
     * @date 2018/6/8
     */
    public String baseMemberPay(HttpServletRequest request, Byte orderType) throws Exception {
        String xml = Util.inputStreamToString(request.getInputStream());
        return appPaymentInfoService.baseMemberPay(xml, orderType);
    }

}
