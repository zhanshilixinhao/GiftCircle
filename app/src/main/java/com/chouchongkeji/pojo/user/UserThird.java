package com.chouchongkeji.pojo.user;

import java.util.Date;

public class UserThird {
    private Integer id;

    private String openId;

    private String phone;

    private Byte type;

    private Date created;

    private Date updated;

    public UserThird(Integer id, String openId, String phone, Byte type, Date created, Date updated) {
        this.id = id;
        this.openId = openId;
        this.phone = phone;
        this.type = type;
        this.created = created;
        this.updated = updated;
    }

    public UserThird() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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