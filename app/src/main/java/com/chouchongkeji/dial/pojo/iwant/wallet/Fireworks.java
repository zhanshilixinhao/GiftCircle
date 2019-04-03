package com.chouchongkeji.dial.pojo.iwant.wallet;

import java.util.Date;

public class Fireworks {
    private Integer id;

    private Integer userId;

    private Integer count;

    private Integer allCount;

    private Date created;

    private Date updated;

    public Fireworks(Integer id, Integer userId, Integer count, Integer allCount, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.count = count;
        this.allCount = allCount;
        this.created = created;
        this.updated = updated;
    }

    public Fireworks() {
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
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