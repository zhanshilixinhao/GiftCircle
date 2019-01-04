package com.chouchongkeji.service.backpack.gift.vo;

import java.util.Date;

/**
 * @author linqin
 * @date 2019/1/4 10:04
 */

public class RecordDetailVo {

    private Integer userId;

    private Integer giftRecordId;

    private Date maxUpdated;

    private Byte status;

    private Integer friendUserId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGiftRecordId() {
        return giftRecordId;
    }

    public void setGiftRecordId(Integer giftRecordId) {
        this.giftRecordId = giftRecordId;
    }

    public Date getMaxUpdated() {
        return maxUpdated;
    }

    public void setMaxUpdated(Date maxUpdated) {
        this.maxUpdated = maxUpdated;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(Integer friendUserId) {
        this.friendUserId = friendUserId;
    }
}
