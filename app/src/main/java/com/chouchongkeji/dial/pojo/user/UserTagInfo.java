package com.chouchongkeji.dial.pojo.user;

import java.util.Date;

public class UserTagInfo {
    private Integer id;

    private Integer userId;

    private Integer friendUserId;

    private String tagIds;

    private Date created;

    private Date updated;

    public UserTagInfo(Integer id, Integer userId, Integer friendUserId, String tagIds, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.friendUserId = friendUserId;
        this.tagIds = tagIds;
        this.created = created;
        this.updated = updated;
    }

    public UserTagInfo() {
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

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds == null ? null : tagIds.trim();
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