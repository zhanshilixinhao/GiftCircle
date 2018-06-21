package com.chouchongkeji.dial.pojo.user;

import java.util.Date;

public class UserPreference {
    private Integer userId;

    private String tags;

    private String giftPreference;

    private Date created;

    private Date updated;

    public UserPreference(Integer userId, String tags, String giftPreference, Date created, Date updated) {
        this.userId = userId;
        this.tags = tags;
        this.giftPreference = giftPreference;
        this.created = created;
        this.updated = updated;
    }

    public UserPreference() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getGiftPreference() {
        return giftPreference;
    }

    public void setGiftPreference(String giftPreference) {
        this.giftPreference = giftPreference == null ? null : giftPreference.trim();
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