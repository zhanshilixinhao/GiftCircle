package com.chouchongkeji.dial.pojo.home;

import java.math.BigDecimal;
import java.util.Date;

public class GiftRecordSelf {
    private Integer id;

    private Integer userId;

    private BigDecimal amount;

    private String event;

    private String detail;

    private String obType;

    private Byte inoutType;

    private Date targetTime;

    private Date created;

    private Date updated;

    public GiftRecordSelf(Integer id, Integer userId, BigDecimal amount, String event, String detail, String obType, Byte inoutType, Date targetTime, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.event = event;
        this.detail = detail;
        this.obType = obType;
        this.inoutType = inoutType;
        this.targetTime = targetTime;
        this.created = created;
        this.updated = updated;
    }

    public GiftRecordSelf() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getObType() {
        return obType;
    }

    public void setObType(String obType) {
        this.obType = obType == null ? null : obType.trim();
    }

    public Byte getInoutType() {
        return inoutType;
    }

    public void setInoutType(Byte inoutType) {
        this.inoutType = inoutType;
    }

    public Date getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Date targetTime) {
        this.targetTime = targetTime;
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