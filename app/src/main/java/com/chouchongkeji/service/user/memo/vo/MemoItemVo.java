package com.chouchongkeji.service.user.memo.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.util.Date;

/**
 * @author yichenshanren
 * @date 2018/6/22
 */

public class MemoItemVo implements Cloneable {

    private Integer id;
    private Integer userId;
    private Date targetTime;
    private Byte isCirculation;
    private String detail;
    private Integer type;
    private String users;
    private Date created;
    private String nickname;

    @ImgUrl
    private String avatar;


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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public Byte getIsCirculation() {
        return isCirculation;
    }

    public void setIsCirculation(Byte isCirculation) {
        this.isCirculation = isCirculation;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
