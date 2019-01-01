package com.chouchongkeji.dial.pojo.backpack;

import java.util.Date;

public class ForRecord {
    private Integer id;

    private Integer userId;

    private Long bpId;

    private Integer friendUserId;

    private Byte status;

    private Byte operation;

    private Date created;

    private Date updated;

    private Byte isDel;

    private Byte isFriendDel;

    public ForRecord(Integer id, Integer userId, Long bpId, Integer friendUserId, Byte status, Byte operation, Date created, Date updated, Byte isDel, Byte isFriendDel) {
        this.id = id;
        this.userId = userId;
        this.bpId = bpId;
        this.friendUserId = friendUserId;
        this.status = status;
        this.operation = operation;
        this.created = created;
        this.updated = updated;
        this.isDel = isDel;
        this.isFriendDel = isFriendDel;
    }

    public ForRecord() {
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

    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
    }

    public Integer getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(Integer friendUserId) {
        this.friendUserId = friendUserId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getOperation() {
        return operation;
    }

    public void setOperation(Byte operation) {
        this.operation = operation;
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

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Byte getIsFriendDel() {
        return isFriendDel;
    }

    public void setIsFriendDel(Byte isFriendDel) {
        this.isFriendDel = isFriendDel;
    }
}
