package com.chouchongkeji.dial.pojo.gift.item;

import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;
import java.util.Date;

public class ItemSku {
    private Integer id;

    private Integer itemId;

    private String title;

    @ImgUrl
    private String cover;

    private String pictures;

    private Integer stock;

    private BigDecimal price;

    private Integer sales;

    private Byte status;

    private Date created;

    private Date updated;

    public ItemSku(Integer id, Integer itemId, String title, String cover, String pictures, Integer stock, BigDecimal price, Integer sales, Byte status, Date created, Date updated) {
        this.id = id;
        this.itemId = itemId;
        this.title = title;
        this.cover = cover;
        this.pictures = pictures;
        this.stock = stock;
        this.price = price;
        this.sales = sales;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    public ItemSku() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures == null ? null : pictures.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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