package com.chouchongkeji.controller.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.iwant.wallet.UserChargeService;
import com.chouchongkeji.util.Constants;
import com.yichen.auth.mvc.AppClient;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2018/6/6
 */
@RestController
@RequestMapping("auth/v1/charge")
public class UserChargeController {

    @Autowired
    private UserChargeService userChargeService;

    /**
     * 创建充值订单
     *
     * @param userDetails
     * @return
     * @author linqin
     * @date 2018/6/6
     */
    @PostMapping("/order")
    public Response chargeOrder(@AuthenticationPrincipal UserDetails userDetails, @AppClient Integer client,
                                BigDecimal amount, Integer payWay) {
        //校验必传参数
        if (amount == null || amount.doubleValue() < 0) {
            return ResponseFactory.err("充值金额错误");
        }
        //检验支付方式
        if (payWay == null || (payWay != Constants.PAY_TYPE.WX && payWay != Constants.PAY_TYPE.ALI)) {
            return ResponseFactory.err("支付方式错误!");
        }

        return userChargeService.chargeOrder(userDetails.getUserId(), client, amount, payWay);
    }


}
