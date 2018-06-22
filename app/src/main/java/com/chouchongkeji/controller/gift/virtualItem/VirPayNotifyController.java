package com.chouchongkeji.controller.gift.virtualItem;

import com.chouchongkeji.goexplore.pay.alipay_v2.ALiPayV2Vo;
import com.chouchongkeji.goexplore.pay.weixin.common.Util;
import com.chouchongkeji.service.gift.virtualItem.VirPayNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yy
 * @date 2018/6/12
 **/

@RestController
public class VirPayNotifyController {
    @Autowired
    private VirPayNotifyService virPayNotifyService;

    /**
     * 充值订单支付宝支付回调
     *
     * @param: [alipayVo, request]
     * @return: java.lang.String
     * @author: yy
     * @Date: 2018/6/12
     */
    @PostMapping("virItem_order/ali")
    public String chargeOrderAliPay(ALiPayV2Vo alipayVo, HttpServletRequest request)throws Exception{
        return virPayNotifyService.orderAliPay(alipayVo,request.getParameterMap(),(byte)3);
    }

    /**
     * 充值订单微信支付回调
     *
     * @param: [request]
     * @return: java.lang.String
     * @author: yy
     * @Date: 2018/6/12
     */
    @PostMapping("virItem_order/wx")
    public String orderWeixinPay(HttpServletRequest request) throws Exception {
        String xml = Util.inputStreamToString(request.getInputStream());

        return virPayNotifyService.orderWXPay(xml,(byte)3);
    }
}
