package com.chouchongkeji.dial.pojo.iwant.wallet;

import java.util.Date;

public class FireworksRecord {
    private Integer id;

    private Integer userId;

    private String describe;

    private Integer count;

    private Integer targetId;

    private Byte type;

    private Date created;

    private Date updated;

    public FireworksRecord(Integer id, Integer userId, String describe, Integer count, Integer targetId, Byte type, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.describe = describe;
        this.count = count;
        this.targetId = targetId;
        this.type = type;
        this.created = created;
        this.updated = updated;
    }

    public FireworksRecord() {
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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