package com.chouchongkeji.service.user.memo.vo;

import com.yichen.auth.jackson.ImgUrl;

import javax.xml.crypto.Data;

/**
 * @author linqin
 * @date 2019/3/12 17:33
 */

public class FriendHumVo {

    private Integer userId; // 用户id
    private Integer friendUserId; // 好友用户id

    private Integer heartNum;// 互动值

    private String nickname;//好友昵称
    @ImgUrl
    private String avatar; //好友头像


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(Integer friendUserId) {
        this.friendUserId = friendUserId;
    }

    public Integer getHeartNum() {
        return heartNum;
    }

    public void setHeartNum(Integer heartNum) {
        this.heartNum = heartNum;
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
