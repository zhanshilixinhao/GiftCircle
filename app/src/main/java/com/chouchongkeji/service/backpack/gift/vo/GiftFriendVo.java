package com.chouchongkeji.service.backpack.gift.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/7/10
 */

public class GiftFriendVo {

    private Integer recordId;
    private String greetting;
    private Byte giftType;
    private String event;
    private Date created;
    private Integer recordDetailId;
    private BigDecimal amount;
    private List<GiftItemVo> gifts;
    private Integer inoutType;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getGreetting() {
        return greetting;
    }

    public void setGreetting(String greetting) {
        this.greetting = greetting;
    }

    public Byte getGiftType() {
        return giftType;
    }

    public void setGiftType(Byte giftType) {
        this.giftType = giftType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getRecordDetailId() {
        return recordDetailId;
    }

    public void setRecordDetailId(Integer recordDetailId) {
        this.recordDetailId = recordDetailId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<GiftItemVo> getGifts() {
        return gifts;
    }

    public void setGifts(List<GiftItemVo> gifts) {
        this.gifts = gifts;
    }

    public Integer getInoutType() {
        return inoutType;
    }

    public void setInoutType(Integer inoutType) {
        this.inoutType = inoutType;
    }
}
