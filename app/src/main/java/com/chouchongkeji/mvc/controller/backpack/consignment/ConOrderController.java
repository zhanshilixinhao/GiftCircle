package com.chouchongkeji.mvc.controller.backpack.consignment;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.consignment.ConOrderService;
import com.chouchongkeji.util.Constants;
import com.yichen.auth.mvc.AppClient;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2018/7/3
 */
@RestController
@RequestMapping("auth/consignment/order")
public class ConOrderController {

    @Autowired
    private ConOrderService conOrderService;

    /**
     * 寄售台订单创建
     *
     * @param userDetails
     * @param client
     * @param consignmentId 寄售台Id
     * @param payWay        支付方式  微信 24656  ，支付宝78990   ，余额 98001
     * @return
     * @author linqin
     * @date 2018/7/3
     */
    @PostMapping("create")
    public Response createOrder(@AuthenticationPrincipal UserDetails userDetails, @AppClient Integer client,
                                Integer consignmentId, Integer payWay) {
        //检验支付方式
        if (payWay == null || (payWay != Constants.PAY_TYPE.WX && payWay != Constants.PAY_TYPE.ALI &&
                payWay != Constants.PAY_TYPE.yue)) {
            return ResponseFactory.err("支付方式错误!");
        }
        //校验寄售台Id
        if (consignmentId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return conOrderService.createOrder(userDetails.getUserId(), client, consignmentId, payWay);
    }

    /**
     * 寄售台订单支付
     *
     * @param userDetails
     * @param orderNo     订单号
     * @param payWay      支付方式  微信 24656  ，支付宝78990   ，余额 98001
     * @return
     * @author linqin
     * @date 2018/7/3
     */
    @PostMapping("pay")
    public Response orderPay(@AuthenticationPrincipal UserDetails userDetails, Long orderNo, Integer payWay) {
        //检验支付方式
        if (payWay == null || (payWay != Constants.PAY_TYPE.WX && payWay != Constants.PAY_TYPE.ALI &&
                payWay != Constants.PAY_TYPE.yue)) {
            return ResponseFactory.err("支付方式错误!");
        }
        if (orderNo == null) {
            return ResponseFactory.errMissingParameter();
        }
        return conOrderService.orderPay(userDetails.getUserId(), orderNo, payWay);
    }


}
