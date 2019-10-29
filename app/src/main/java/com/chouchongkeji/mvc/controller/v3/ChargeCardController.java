package com.chouchongkeji.mvc.controller.v3;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.v3.ChargeCardService;
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
 * @date 2019/10/29
 */
@RestController
public class ChargeCardController {

    @Autowired
    private ChargeCardService chargeCardService;

    /**
     * 礼遇圈会员卡充值规则(活动)
     *
     * @return
     * @author linqin
     * @date 2019/10/29
     */
    @PostMapping("noauth/v3/charge/rule/list")
    public Response getChargeRuleList() {
        return chargeCardService.getChargeRuleList();
    }


    /**
     * 创建礼遇圈会员卡充值订单
     *
     * @param userDetails
     * @param eventId     活动id
     * @return
     * @author linqin
     * @date 2019/10/29
     */
    @PostMapping("auth/v3/charge/order")
    public Response createdOrder(@AuthenticationPrincipal UserDetails userDetails, @AppClient Integer client, Integer eventId, Integer payWay) {
        if (eventId == null){
            return ResponseFactory.errMissingParameter();
        }
        //检验支付方式
        if (payWay == null || (payWay != Constants.PAY_TYPE.WX && payWay != Constants.PAY_TYPE.ALI)) {
            return ResponseFactory.err("支付方式错误!");
        }
        return chargeCardService.createdOrder(userDetails.getUserId(),client,eventId,payWay);
    }

}
