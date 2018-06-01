package com.chouchongkeji.goexplore.pay;

/**
 * 订单支付请求结果
 *
 * @author yichenshanren
 * @date 2017/10/25
 */

public class PayResultVo {

    private String params;
//    private String params1;
    private Long orderNo;
    private Integer type;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
//
//    public String getParams1() {
//        return params1;
//    }
//
//    public void setParams1(String params1) {
//        this.params1 = params1;
//    }
}