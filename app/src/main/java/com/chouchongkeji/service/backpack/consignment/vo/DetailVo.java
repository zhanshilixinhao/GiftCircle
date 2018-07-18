package com.chouchongkeji.service.backpack.consignment.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author linqin
 * @date 2018/7/3
 */
public class DetailVo {

    private Integer consignmentId; //寄售台Id

    private Integer itemId; //商品Id

    private BigDecimal price;//商品价格

    @ImgUrl
    private List<String> pictures; //图片集

    private String title; //标题

    private String description; //描述

    private String detailUrl;//详情地址

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public Integer getConsignmentId() {
        return consignmentId;
    }

    public void setConsignmentId(Integer consignmentId) {
        this.consignmentId = consignmentId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
