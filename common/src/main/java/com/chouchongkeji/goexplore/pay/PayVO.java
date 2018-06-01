package com.chouchongkeji.goexplore.pay;

import java.math.BigDecimal;

/**
 * @author yichenshanren
 * @date 2017/10/19
 */

public class PayVO {

    private String body;
    private String subject;
    // 异步回掉地址
    private String url;
    private long orderNo;
    private BigDecimal price;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }
}
