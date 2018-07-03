package com.chouchongkeji.dial.pojo.gift.virtualItem;

import java.math.BigDecimal;
import java.util.Date;

public class GiftRecordDetail {
    private Integer id;

    private Integer giftRecordId;

    private Integer userId;

    private Long bpId;

    private Integer quantity;

    private BigDecimal price;

    private String reply;

    private Byte type;

    private Byte status;

    private Date updated;

    private Date created;

    public GiftRecordDetail(Integer id, Integer giftRecordId, Integer userId, Long bpId, Integer quantity, BigDecimal price, String reply, Byte type, Byte status, Date updated, Date created) {
        this.id = id;
        this.giftRecordId = giftRecordId;
        this.userId = userId;
        this.bpId = bpId;
        this.quantity = quantity;
        this.price = price;
        this.reply = reply;
        this.type = type;
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

    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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