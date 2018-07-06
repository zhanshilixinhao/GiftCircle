package com.chouchongkeji.service.backpack.gift.vo;

import java.util.Date;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/7/6
 */

public class GiftTaskVo {

    private Integer recordId;
    private Integer sendUserId;
    private Integer recordDetailId;
    private Integer userId;
    private List<GiftItemVo> giftItems;
    private Date targetTime;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Integer getRecordDetailId() {
        return recordDetailId;
    }

    public void setRecordDetailId(Integer recordDetailId) {
        this.recordDetailId = recordDetailId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<GiftItemVo> getGiftItems() {
        return giftItems;
    }

    public void setGiftItems(List<GiftItemVo> giftItems) {
        this.giftItems = giftItems;
    }

    public Date getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Date targetTime) {
        this.targetTime = targetTime;
    }
}
