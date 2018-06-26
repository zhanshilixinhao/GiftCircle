package com.chouchongkeji.service.user.friend.vo;

import com.yichen.auth.jackson.ImgUrl;

/**
 * @author yichenshanren
 * @date 2018/6/26
 */

public class CommentUserVo {

    private Integer userId;
    private String nickname;
    private String remark;
    private String relationship;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @ImgUrl
    private String avatar;


}
