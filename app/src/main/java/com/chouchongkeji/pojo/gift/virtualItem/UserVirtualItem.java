package com.chouchongkeji.pojo.gift.virtualItem;

import java.math.BigDecimal;
import java.util.Date;

public class UserVirtualItem {
    private Integer id;

    private Integer userId;

    private Integer virtualItemId;

    private Integer quantity;

    private String name;

    private String cover;

    private String summary;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private Date created;

    private Date updated;

    public UserVirtualItem(Integer id, Integer userId, Integer virtualItemId, Integer quantity, String name, String cover, String summary, BigDecimal price, BigDecimal totalPrice, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.virtualItemId = virtualItemId;
        this.quantity = quantity;
        this.name = name;
        this.cover = cover;
        this.summary = summary;
        this.price = price;
        this.totalPrice = totalPrice;
        this.created = created;
        this.updated = updated;
    }

    public UserVirtualItem() {
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

    public Integer getVirtualItemId() {
        return virtualItemId;
    }

    public void setVirtualItemId(Integer virtualItemId) {
        this.virtualItemId = virtualItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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