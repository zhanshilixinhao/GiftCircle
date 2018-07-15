package com.chouchongkeji.service.backpack.gift.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.util.Date;

/**
 * @author linqin
 * @date 2018/7/15
 */
public class GiftExListVo {
    private Integer id;

    private Integer userId;

    private Integer friendUserId;

    private String friendNickname;

    @ImgUrl
    private String friendAvatar;

    private Integer type;//1-用户向好友提成交换 ，2-好友向用户提成交换

    private Byte status;

    private Date created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getFriendNickname() {
        return friendNickname;
    }

    public void setFriendNickname(String friendNickname) {
        this.friendNickname = friendNickname;
    }

    public String getFriendAvatar() {
        return friendAvatar;
    }

    public void setFriendAvatar(String friendAvatar) {
        this.friendAvatar = friendAvatar;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
