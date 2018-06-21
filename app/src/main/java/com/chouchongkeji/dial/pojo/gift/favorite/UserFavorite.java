package com.chouchongkeji.dial.pojo.gift.favorite;

import java.util.Date;

public class UserFavorite {
    private Integer id;

    private Integer userId;

    private Integer targetId;

    private Byte type;

    private Date created;

    private Date updated;

    public UserFavorite(Integer id, Integer userId, Integer targetId, Byte type, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.targetId = targetId;
        this.type = type;
        this.created = created;
        this.updated = updated;
    }

    public UserFavorite() {
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