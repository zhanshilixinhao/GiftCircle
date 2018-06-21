package com.chouchongkeji.service.friend.vo;

import com.chouchongkeji.dial.pojo.friend.Friend;

/**
 * @author yichenshanren
 * @date 2018/6/21
 */

public class FriendItem extends Friend {

    private String avatar;
    private String nickname;
    private Integer heartNum = 0;

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

    public Integer getHeartNum() {
        return heartNum;
    }

    public void setHeartNum(Integer heartNum) {
        this.heartNum = heartNum;
    }
}
