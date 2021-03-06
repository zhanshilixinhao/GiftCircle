package com.chouchongkeji.service.backpack.gift.vo;

import java.util.Date;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/7/2
 */

public class GiftSendVo {

    private Long bpId;
    private Integer friendUserId;
    private List<Long> subBpIds;
    private String greeting;
    private Byte type; // 1 未领取 2 已领取部分 3 已领取全部 4 超时领取失败
    private String event;
    private Date targetTime;
    private Float p;

    public Float getP() {
        return p;
    }

    public void setP(Float p) {
        this.p = p;
    }

    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
    }

    public Integer getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(Integer friendUserId) {
        this.friendUserId = friendUserId;
    }

    public List<Long> getSubBpIds() {
        return subBpIds;
    }

    public void setSubBpIds(List<Long> subBpIds) {
        this.subBpIds = subBpIds;
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

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
