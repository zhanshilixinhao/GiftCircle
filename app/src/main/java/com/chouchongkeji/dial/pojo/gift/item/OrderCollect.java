package com.chouchongkeji.dial.pojo.gift.item;

import java.util.Date;

public class OrderCollect {
    private Integer id;

    private Long hOrderNo;

    private Long orderNo;

    private Date created;

    private Date updated;

    public OrderCollect(Integer id, Long hOrderNo, Long orderNo, Date created, Date updated) {
        this.id = id;
        this.hOrderNo = hOrderNo;
        this.orderNo = orderNo;
        this.created = created;
        this.updated = updated;
    }

    public OrderCollect() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long gethOrderNo() {
        return hOrderNo;
    }

    public void sethOrderNo(Long hOrderNo) {
        this.hOrderNo = hOrderNo;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}