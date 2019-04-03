package com.chouchongkeji.dial.pojo.user;

import java.util.Date;

public class InviteUser {
    private Integer id;

    private Integer userId;

    private Integer parentUserId;

    private Date created;

    private Date updated;

    public InviteUser(Integer id, Integer userId, Integer parentUserId, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.parentUserId = parentUserId;
        this.created = created;
        this.updated = updated;
    }

    public InviteUser() {
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

    public Integer getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(Integer parentUserId) {
        this.parentUserId = parentUserId;
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