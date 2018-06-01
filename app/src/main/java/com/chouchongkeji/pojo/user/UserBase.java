package com.chouchongkeji.pojo.user;

import java.util.Date;

public class UserBase {
    private Integer id;

    private String password;

    private String account;

    private String phone;

    private String avatar;

    private String nickname;

    private Byte type;

    private Byte status;

    private String lastIp;

    private String lastClien;

    private Date created;

    private Date updated;

    public UserBase(Integer id, String password, String account, String phone, String avatar, String nickname, Byte type, Byte status, String lastIp, String lastClien, Date created, Date updated) {
        this.id = id;
        this.password = password;
        this.account = account;
        this.phone = phone;
        this.avatar = avatar;
        this.nickname = nickname;
        this.type = type;
        this.status = status;
        this.lastIp = lastIp;
        this.lastClien = lastClien;
        this.created = created;
        this.updated = updated;
    }

    public UserBase() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp == null ? null : lastIp.trim();
    }

    public String getLastClien() {
        return lastClien;
    }

    public void setLastClien(String lastClien) {
        this.lastClien = lastClien == null ? null : lastClien.trim();
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