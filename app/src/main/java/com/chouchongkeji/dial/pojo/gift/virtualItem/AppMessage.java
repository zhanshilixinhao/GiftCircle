package com.chouchongkeji.dial.pojo.gift.virtualItem;

import com.chouchongkeji.util.Constants;

import java.util.Date;

public class AppMessage {
    private Integer id;

    private String title;

    private String sumarry;

    private String content;

    private Integer targetId;

    private Byte targetType;

    private Byte messageType;

    private Date created;

    private Date updated;

    public AppMessage(Integer id, String title, String sumarry, String content, Integer targetId, Byte targetType, Byte messageType, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.sumarry = sumarry;
        this.content = content;
        this.targetId = targetId;
        this.targetType = targetType;
        this.messageType = messageType;
        this.created = created;
        this.updated = updated;
    }

    public AppMessage() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSumarry() {
        return sumarry;
    }

    public void setSumarry(String sumarry) {
        this.sumarry = sumarry == null ? null : sumarry.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Byte getTargetType() {
        return targetType;
    }

    public void setTargetType(Byte targetType) {
        this.targetType = targetType;
    }

    public Byte getMessageType() {
        return messageType;
    }

    public void setMessageType(Byte messageType) {
        this.messageType = messageType;
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

    public void messageType(Constants.APP_MESSAGE_TYPE type) {
        setMessageType(type.type());
        setTitle(type.title());
    }
}