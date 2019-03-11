package com.chouchongkeji.dial.pojo.user.memo;

import java.util.Date;

public class MemoEventType {
    private Integer id;

    private Integer userId;

    private String name;

    private Date created;

    private Date updated;

    public MemoEventType(Integer id, Integer userId, String name, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.created = created;
        this.updated = updated;
    }

    public MemoEventType() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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