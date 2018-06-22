package com.chouchongkeji.dial.pojo.user.memo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MemoActivity {
    private Integer id;

    private Integer userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date targetTime;

    private String address;

    private Integer count;

    private String title;

    private String detail;

    private String users;

    private Date created;

    private Date updated;

    public MemoActivity(Integer id, Integer userId, Date targetTime, String address, Integer count, String title, String detail, String users, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.targetTime = targetTime;
        this.address = address;
        this.count = count;
        this.title = title;
        this.detail = detail;
        this.users = users;
        this.created = created;
        this.updated = updated;
    }

    public MemoActivity() {
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

    public Date getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Date targetTime) {
        this.targetTime = targetTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users == null ? null : users.trim();
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