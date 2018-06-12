package com.chouchongkeji.vo;

import java.math.BigDecimal;

/**
 * 用于创建订单支付
 *
 * @author yy
 * @date 2018/6/12
 **/
public class BaseOrderVo {
    // 订单号
    private Long orderNo;
    // 订单价格
    private BigDecimal totalPrice;

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
