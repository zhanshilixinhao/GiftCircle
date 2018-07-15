package com.chouchongkeji.dial.pojo.backpack.gift;

import com.chouchongkeji.service.backpack.gift.vo.GiftExItemVo;

import java.util.Date;
import java.util.List;

public class GiftExchange {
    private Integer id;

    private Integer userId;

    private Integer friendUserId;

    private List<GiftExItemVo> exchangeGifts;

    private List<GiftExItemVo> wantGifts;

    private List<GiftExItemVo> submitGifts;

    private Byte status;

    private Date created;

    private Date updated;

    public GiftExchange(Integer id, Integer userId, Integer friendUserId, List<GiftExItemVo> exchangeGifts, List<GiftExItemVo> wantGifts, List<GiftExItemVo> submitGifts, Byte status, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.friendUserId = friendUserId;
        this.exchangeGifts = exchangeGifts;
        this.wantGifts = wantGifts;
        this.submitGifts = submitGifts;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    public GiftExchange() {
        super();
    }

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

    public List<GiftExItemVo> getExchangeGifts() {
        return exchangeGifts;
    }

    public void setExchangeGifts(List<GiftExItemVo> exchangeGifts) {
        this.exchangeGifts = exchangeGifts;
    }

    public List<GiftExItemVo> getWantGifts() {
        return wantGifts;
    }

    public void setWantGifts(List<GiftExItemVo> wantGifts) {
        this.wantGifts = wantGifts;
    }

    public List<GiftExItemVo> getSubmitGifts() {
        return submitGifts;
    }

    public void setSubmitGifts(List<GiftExItemVo> submitGifts) {
        this.submitGifts = submitGifts;
    }

    public Integer getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(Integer friendUserId) {
        this.friendUserId = friendUserId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}