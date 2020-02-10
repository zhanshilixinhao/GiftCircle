package com.chouchongkeji.mvc.controller.v3;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.v3.ElCouponService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2020/2/10 10:25
 */
@RestController
@RequestMapping("auth/v3/coupon")
public class ElCouponController {

    @Autowired
    private ElCouponService elCouponService;

    /**
     * 获取电子券列表
     *
     * @param userDetails
     * @param page
     * @return
     */
    @PostMapping("list")
    public Response getElCouponList(@AuthenticationPrincipal UserDetails userDetails, PageQuery page) {
        return elCouponService.getElCouponList(userDetails.getUserId(), page);
    }

    /**
     * 赠送优惠券
     *
     * @param userDetails
     * @param num    优惠券编号
     * @param quantity    数量
     * @return
     */
    @PostMapping("send")
    public Response sendCoupon(@AuthenticationPrincipal UserDetails userDetails,
                               Long num, Integer quantity) {
        if (num == null || quantity == null) {
            return ResponseFactory.errMissingParameter();
        }
        if (quantity <= 0) {
            return ResponseFactory.err("请输入正确的数量");
        }
        return elCouponService.sendCoupon(userDetails.getUserId(),num,quantity);
    }

    /**
     * 判断是否可以领取优惠券
     * @param userDetails
     * @param couponSendId 优惠券赠送id
     * @return
     */
    @PostMapping("judge")
    public Response judgeSendCoupon(@AuthenticationPrincipal UserDetails userDetails,Integer couponSendId){
        if (couponSendId == null){
            return ResponseFactory.errMissingParameter();
        }
        return elCouponService.judgeSendCoupon(userDetails.getUserId(),couponSendId);
    }

    /**
     * 领取优惠券
     * @param userDetails
     * @param couponSendId 优惠券赠送id
     * @return
     */
    @PostMapping("get")
    public Response getElCoupon(@AuthenticationPrincipal UserDetails userDetails,Integer couponSendId){
        if (couponSendId == null){
            return ResponseFactory.errMissingParameter();
        }
        return elCouponService.getElCoupon(userDetails.getUserId(),couponSendId);
    }

}
