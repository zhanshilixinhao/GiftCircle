package com.chouchongkeji.service.user.friend.vo;

import com.yichen.auth.jackson.ImgUrl;

/**
 * @author yichenshanren
 * @date 2018/6/26
 */

public class PraiseVo {

    private Integer praiseUserId;
    private Integer momentId;
    @ImgUrl
    private String avatar;

    public Integer getPraiseUserId() {
        return praiseUserId;
    }

    public void setPraiseUserId(Integer praiseUserId) {
        this.praiseUserId = praiseUserId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }
}
