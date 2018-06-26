package com.chouchongkeji.dial.pojo.user.memo;

import java.util.Date;

public class MomentPraise {
    private Integer id;

    private Integer momentId;

    private Integer userId;

    private Date created;

    private Date updated;

    public MomentPraise(Integer id, Integer momentId, Integer userId, Date created, Date updated) {
        this.id = id;
        this.momentId = momentId;
        this.userId = userId;
        this.created = created;
        this.updated = updated;
    }

    public MomentPraise() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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