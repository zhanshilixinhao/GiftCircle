package com.chouchongkeji.service.backpack.gift.vo2;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * 礼物赠送2.0
 * @author yichenshanren
 * @date 2018/7/2
 */

public class GiftSendVo2 {

    private Long bpId;  //背包id
    private HashSet<Integer> friendUserIds;  //好友id
    private String greeting; //祝福语
    private Byte type; // 1 未领取 2 已领取部分 3 已领取全部 4 超时领取失败
    private String event; //事件
    private Date targetTime; //赠送时间

    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
    }

    public HashSet<Integer> getFriendUserIds() {
        return friendUserIds;
    }

    public void setFriendUserIds(HashSet<Integer> friendUserIds) {
        this.friendUserIds = friendUserIds;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Date targetTime) {
        this.targetTime = targetTime;
    }
}
