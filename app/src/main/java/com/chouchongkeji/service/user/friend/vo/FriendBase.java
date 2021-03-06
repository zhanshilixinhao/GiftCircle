package com.chouchongkeji.service.user.friend.vo;

import com.yichen.auth.jackson.ImgUrl;

/**
 * @author yichenshanren
 * @date 2018/6/21
 */

public class FriendBase {

    private Integer userId;
    @ImgUrl
    private String avatar;
    private String nickname;
    private Integer isFriend;
    private Integer heartNum; //互动值

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(Integer isFriend) {
        this.isFriend = isFriend;
    }

    public Integer getHeartNum() {
        return heartNum;
    }

    public void setHeartNum(Integer heartNum) {
        this.heartNum = heartNum;
    }
}
