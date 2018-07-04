package com.chouchongkeji.service.backpack.consignment;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author linqin
 * @date 2018/7/3
 */
public interface ConOrderService {

    /**
     * 寄售台订单创建
     *
     * @param userId        用户id
     * @param client
     * @param consignmentId 寄售台Id
     * @param payWay        支付方式  微信 24656  ，支付宝78990   ，余额 98001
     * @return
     * @author linqin
     * @date 2018/7/3
     */
    Response createOrder(Integer userId, Integer client, Integer consignmentId, Integer payWay);

    /**
     * 寄售台订单支付
     *
     * @param userId  用户id
     * @param orderNo 订单号
     * @param payWay  支付方式  微信 24656  ，支付宝78990   ，余额 98001
     * @return
     * @author linqin
     * @date 2018/7/3
     */
    Response orderPay(Integer userId, Long orderNo, Integer payWay);
}
