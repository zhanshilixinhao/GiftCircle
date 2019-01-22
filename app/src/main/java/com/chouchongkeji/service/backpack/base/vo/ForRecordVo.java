package com.chouchongkeji.service.backpack.base.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linqin
 * @date 2018/7/12
 */
public class ForRecordVo {

    private Integer id;

    private Integer userId;

    private Long bpId;

    private Integer friendUserId;

    private Byte status;

    private Byte operation;

    private Integer type;//类型 1-用户向好友索要商品 ，2-好友向用户索要商品

    @ImgUrl
    private String cover;//商品图标

    private String title;//商品标题

    private BigDecimal price;//商品价格

    private String brand;//商品品牌

    @ImgUrl
    private String avatar;//好友头像

    private String nickname;//好友昵称

    private Date created;

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

    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
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

    public Byte getOperation() {
        return operation;
    }

    public void setOperation(Byte operation) {
        this.operation = operation;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
