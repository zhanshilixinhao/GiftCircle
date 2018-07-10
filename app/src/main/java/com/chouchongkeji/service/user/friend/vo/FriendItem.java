package com.chouchongkeji.service.user.friend.vo;

import com.chouchongkeji.dial.pojo.friend.Friend;
import com.yichen.auth.jackson.ImgUrl;

/**
 * @author yichenshanren
 * @date 2018/6/21
 */

public class FriendItem extends FriendVo {


    private Integer heartNum = 0;

    public Integer getHeartNum() {
        return heartNum;
    }

    public void setHeartNum(Integer heartNum) {
        this.heartNum = heartNum;
    }
}
