package com.chouchongkeji.service.gift.item.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户展示商品详情
 *
 * @author yy
 * @date 2018/6/13
 **/
public class ItemDetail {
    // 商品id
    private Integer id;
    // 商品名称
    private String title;
    // 价格
    private BigDecimal price;
    // 销量
    private Integer sales;
    // 商品属性
    private String description;
    // 商品图片
    private List<String> pictures;
    // 详情地址
    private String detailUrl;
    // 是否收藏商品 1.收藏 2.未收藏
    private Integer isCollect;

    public Integer getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Integer isCollect) {
        this.isCollect = isCollect;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
