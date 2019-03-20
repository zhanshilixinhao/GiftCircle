package com.chouchongkeji.dial.pojo.user;

import java.util.Date;

public class LeaveMessage {
    private Integer id;

    private Integer userId;

    private Integer friendUserId;

    private String detail;

    private Date created;

    private Date updated;

    public LeaveMessage(Integer id, Integer userId, Integer friendUserId, String detail, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.friendUserId = friendUserId;
        this.detail = detail;
        this.created = created;
        this.updated = updated;
    }

    public LeaveMessage() {
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

    public Integer getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(Integer friendUserId) {
        this.friendUserId = friendUserId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
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