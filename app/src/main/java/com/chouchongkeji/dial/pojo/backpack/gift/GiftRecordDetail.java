package com.chouchongkeji.dial.pojo.backpack.gift;

import java.math.BigDecimal;
import java.util.Date;

public class GiftRecordDetail {
    private Integer id;

    private Integer giftRecordId;

    private Integer userId;

    private BigDecimal amount;

    private String content;

    private Byte isReply;

    private String reply;

    private Byte status;

    private Date updated;

    private Date created;

    public GiftRecordDetail(Integer id, Integer giftRecordId, Integer userId, BigDecimal amount, String content, Byte isReply, String reply, Byte status, Date updated, Date created) {
        this.id = id;
        this.giftRecordId = giftRecordId;
        this.userId = userId;
        this.amount = amount;
        this.content = content;
        this.isReply = isReply;
        this.reply = reply;
        this.status = status;
        this.updated = updated;
        this.created = created;
    }

    public GiftRecordDetail() {
        super();
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
        this.reply = reply == null ? null : reply.trim();
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}