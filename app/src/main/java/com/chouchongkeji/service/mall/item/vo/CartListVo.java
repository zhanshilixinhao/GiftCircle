package com.chouchongkeji.service.mall.item.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linqin
 * @date 2018/6/14
 */
public class CartListVo {
    private Integer itemId;
    private Integer skuId;
    @ImgUrl
    private String cover;
    private String title;
    private String sp; // 规格
    private Integer quantity;
    private BigDecimal price;
    private Date created;
    private Byte status; //商品状态
    private Byte skuStatus; //商品sku状态


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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getSkuStatus() {
        return skuStatus;
    }

    public void setSkuStatus(Byte skuStatus) {
        this.skuStatus = skuStatus;
    }
}
