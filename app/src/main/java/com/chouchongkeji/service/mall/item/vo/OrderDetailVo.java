package com.chouchongkeji.service.mall.item.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2018/6/28
 */
public class OrderDetailVo {

    private Integer itemId;

    private Integer skuId;

    private String title;

    private BigDecimal price;

    @ImgUrl
    private String cover;

    private Integer quantity;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
