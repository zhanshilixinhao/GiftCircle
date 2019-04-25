package com.chouchongkeji.service.user.info.vo;

import com.chouchongkeji.dial.pojo.user.UserTagDict;

/**
 * @author linqin
 * @date 2019/4/25
 */

public class TagVo extends UserTagDict {

    private Integer userId;

    private Integer friendUserId;

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
}
