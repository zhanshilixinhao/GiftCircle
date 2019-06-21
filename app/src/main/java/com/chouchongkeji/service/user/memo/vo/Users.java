package com.chouchongkeji.service.user.memo.vo;

import com.yichen.auth.jackson.ImgUrl;

/**
 * @author linqin
 * @date 2019/6/21
 */

public class Users {
    private Integer userId;

    private String nickname;

    @ImgUrl
    private String avatar;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}
