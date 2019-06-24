package com.chouchongkeji.service.user.friend.vo;

import com.chouchongkeji.dial.pojo.user.AppUser;

/**
 * @author linqin
 * @date 2019/6/24
 */

public class UserFriendVo  extends AppUser {
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
