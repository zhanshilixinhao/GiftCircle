package com.chouchongkeji.dial.pojo.user.memo;

import java.util.Date;

public class MemonEvent {
    private Integer id;

    private Integer userId;

    private Date eventTime;

    private Date targetTime;

    private String title;

    private String detail;

    private Byte isPublic;

    private Date created;

    private Date updated;

    public MemonEvent(Integer id, Integer userId, Date eventTime, Date targetTime, String title, String detail, Byte isPublic, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.eventTime = eventTime;
        this.targetTime = targetTime;
        this.title = title;
        this.detail = detail;
        this.isPublic = isPublic;
        this.created = created;
        this.updated = updated;
    }

    public MemonEvent() {
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

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public Date getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Date targetTime) {
        this.targetTime = targetTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Byte getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Byte isPublic) {
        this.isPublic = isPublic;
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