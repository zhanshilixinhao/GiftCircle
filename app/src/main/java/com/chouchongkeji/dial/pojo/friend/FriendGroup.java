package com.chouchongkeji.dial.pojo.friend;

import java.util.Date;

public class FriendGroup {
    private Integer id;

    private Integer userId;

    private String name;

    private Integer sort;

    private Date created;

    private Date updated;

    public FriendGroup(Integer id, Integer userId, String name, Integer sort, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.sort = sort;
        this.created = created;
        this.updated = updated;
    }

    public FriendGroup() {
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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