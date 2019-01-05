package com.chouchongkeji.service.backpack.gift.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author linqin
 * @date 2019/1/5 20:28
 */

public class GiftRecordDetailVo {
    private Integer id;

    private Integer giftRecordId;

    private Integer userId;

    private BigDecimal amount;

    private List<GiftItemVo> content;

    private Byte isReply;

    private String reply;

    private Byte status;

    private Date updated;

    private Date created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGiftRecordId() {
        return giftRecordId;
    }

    public void setGiftRecordId(Integer giftRecordId) {
        this.giftRecordId = giftRecordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<GiftItemVo> getContent() {
        return content;
    }

    public void setContent(List<GiftItemVo> content) {
        this.content = content;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
