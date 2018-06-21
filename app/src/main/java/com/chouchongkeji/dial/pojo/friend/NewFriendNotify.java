package com.chouchongkeji.dial.pojo.friend;

import java.util.Date;

public class NewFriendNotify {
    private Integer id;

    private Integer userId;

    private Integer targetUserId;

    private Byte notifyType;

    private Byte status;

    private Byte userStatus;

    private Byte targetUserStatus;

    private String content;

    private String validationMsg;

    private String reply;

    private Date created;

    private Date updated;

    public NewFriendNotify(Integer id, Integer userId, Integer targetUserId, Byte notifyType, Byte status, Byte userStatus, Byte targetUserStatus, String content, String validationMsg, String reply, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.targetUserId = targetUserId;
        this.notifyType = notifyType;
        this.status = status;
        this.userStatus = userStatus;
        this.targetUserStatus = targetUserStatus;
        this.content = content;
        this.validationMsg = validationMsg;
        this.reply = reply;
        this.created = created;
        this.updated = updated;
    }

    public NewFriendNotify() {
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

    public Integer getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Integer targetUserId) {
        this.targetUserId = targetUserId;
    }

    public Byte getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(Byte notifyType) {
        this.notifyType = notifyType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Byte userStatus) {
        this.userStatus = userStatus;
    }

    public Byte getTargetUserStatus() {
        return targetUserStatus;
    }

    public void setTargetUserStatus(Byte targetUserStatus) {
        this.targetUserStatus = targetUserStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getValidationMsg() {
        return validationMsg;
    }

    public void setValidationMsg(String validationMsg) {
        this.validationMsg = validationMsg == null ? null : validationMsg.trim();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
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