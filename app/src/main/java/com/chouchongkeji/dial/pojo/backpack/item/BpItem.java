package com.chouchongkeji.dial.pojo.backpack.item;

import java.math.BigDecimal;
import java.util.Date;

public class BpItem {
    private Long id;

    private Integer userId;

    private Integer itemId;

    private Long orderNo;

    private Integer skuId;

    private Integer quantity;

    private BigDecimal price;

    private Date created;

    private Date updated;

    public BpItem(Long id, Integer userId, Integer itemId, Long orderNo, Integer skuId, Integer quantity, BigDecimal price, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.orderNo = orderNo;
        this.skuId = skuId;
        this.quantity = quantity;
        this.price = price;
        this.created = created;
        this.updated = updated;
    }

    public BpItem() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
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

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}