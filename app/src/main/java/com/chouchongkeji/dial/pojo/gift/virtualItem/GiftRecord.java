package com.chouchongkeji.dial.pojo.gift.virtualItem;

import java.util.Date;

public class GiftRecord {
    private Integer id;

    private Integer userId;

    private String greetting;

    private Byte type;

    private String event;

    private Date targetTime;

    private Byte status;

    private Date updated;

    private Date created;

    private Float p;

    public GiftRecord(Integer id, Integer userId, String greetting, Byte type, String event, Date targetTime, Byte status, Date updated, Date created, Float p) {
        this.id = id;
        this.userId = userId;
        this.greetting = greetting;
        this.type = type;
        this.event = event;
        this.targetTime = targetTime;
        this.status = status;
        this.updated = updated;
        this.created = created;
        this.p = p;
    }

    public Float getP() {
        return p;
    }

    public void setP(Float p) {
        this.p = p;
    }

    public GiftRecord() {
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

    public String getGreetting() {
        return greetting;
    }

    public void setGreetting(String greetting) {
        this.greetting = greetting == null ? null : greetting.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    public Date getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Date targetTime) {
        this.targetTime = targetTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}