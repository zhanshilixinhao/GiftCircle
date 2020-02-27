package com.chouchongkeji.service.v3;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

public interface ElCouponService {
    /**
     * 获取电子券列表
     * @param userId
     * @param page
     * @return
     */
    Response getElCouponList(Integer userId, PageQuery page);

    /**
     * 赠送优惠券
     *
     * @param userId
     * @param num    优惠券编号
     * @param quantity    数量
     * @return
     */
    Response sendCoupon(Integer userId, Long num, Integer quantity);

    /**
     * 判断是否可以领取优惠券
     * @param userId
     * @param couponSendId 优惠券赠送id
     * @return
     */
    Response judgeSendCoupon(Integer userId, Integer couponSendId);

    /**
     * 领取优惠券
     * @param userId
     * @param couponSendId 优惠券赠送id
     * @return
     */
    Response getElCoupon(Integer userId, Integer couponSendId);

    /**
     * 获取电子券详情
     *
     * @param userDetails
     * @return
     */
    Response getElCouponDetail(Integer userId, Long num);
}
