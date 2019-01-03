package com.chouchongkeji.service.backpack.gift.vo;

import java.util.Date;
import java.util.List;

/**
 * @author linqin
 * @date 2019/1/2 9:31
 */

public class GiftSendListVo {

    private Integer id; // 送礼记录id

    private Integer userId; //送礼者id

    private Integer friendUserId; //接收礼物者id

    private String greetting; // 祝福语

    private Byte type; //

    private String event; // 事件名称

    private Date target_time; //目标赠送时间

    private Byte status;

    private List<GiftItemVo> content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getGreetting() {
        return greetting;
    }

    public void setGreetting(String greetting) {
        this.greetting = greetting;
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

    public Date getTarget_time() {
        return target_time;
    }

    public void setTarget_time(Date target_time) {
        this.target_time = target_time;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public List<GiftItemVo> getContent() {
        return content;
    }

    public void setContent(List<GiftItemVo> content) {
        this.content = content;
    }
}
