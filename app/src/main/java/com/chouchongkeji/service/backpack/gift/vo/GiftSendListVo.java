package com.chouchongkeji.service.backpack.gift.vo;

import java.util.Date;
import java.util.List;

/**
 * 赠送礼物列表
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

    private Date targetTime; //目标赠送时间

    private Byte status;

    private List<GiftItemVo> content;

    private Byte isReply;

    private String reply;

    private Date updated;

    private String nickname;

    private String avatar;

    private String friendNickname;

    private String friendAvatar;

    private Byte flag; //1赠送记录  2 收礼记录

//    private Integer quantity; //附属品数量

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

    public Date getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Date targetTime) {
        this.targetTime = targetTime;
    }

    public Byte getIsReply() {
        return isReply;
    }

    public void setIsReply(Byte isReply) {
        this.isReply = isReply;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
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

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
}
