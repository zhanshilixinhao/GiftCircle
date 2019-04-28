package com.chouchongkeji.service.message.vo;

import com.chouchongkeji.service.backpack.gift.vo.GiftItemVo;
import com.yichen.auth.jackson.ImgUrl;

import java.util.Date;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/7/6
 */

public class GiftMessageVo {

    private String summary;
    private String messageType;
    private Integer recordDetailId;
    private Integer messageId;
    private Integer userId;
    private Byte isRead;
    private String greetting;
    private List<GiftItemVo> giftItems;
    private Integer sendUserId;

    @ImgUrl
    private String sendAvatar;

    @ImgUrl
    private String avatar;
    private String nickname;
    private String remark;
    private Integer friendId;
    private String relationShip;

    private String reply;
    private Byte isReply;

    private Date created;
    private Date targetTime;

    private Integer reNum; // 剩余数量

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Integer getRecordDetailId() {
        return recordDetailId;
    }

    public void setRecordDetailId(Integer recordDetailId) {
        this.recordDetailId = recordDetailId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Date targetTime) {
        this.targetTime = targetTime;
    }

    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    public String getGreetting() {
        return greetting;
    }

    public void setGreetting(String greetting) {
        this.greetting = greetting;
    }

    public List<GiftItemVo> getGiftItems() {
        return giftItems;
    }

    public void setGiftItems(List<GiftItemVo> giftItems) {
        this.giftItems = giftItems;
    }

    public Integer getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Byte getIsReply() {
        return isReply;
    }

    public void setIsReply(Byte isReply) {
        this.isReply = isReply;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getReNum() {
        return reNum;
    }

    public void setReNum(Integer reNum) {
        this.reNum = reNum;
    }

    public String getSendAvatar() {
        return sendAvatar;
    }

    public void setSendAvatar(String sendAvatar) {
        this.sendAvatar = sendAvatar;
    }
}
