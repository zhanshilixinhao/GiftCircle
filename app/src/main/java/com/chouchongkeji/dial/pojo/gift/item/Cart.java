package com.chouchongkeji.dial.pojo.gift.item;

import java.math.BigDecimal;
import java.util.Date;

public class Cart {
    private Integer id;

    private Integer userId;

    private Integer itemId;

    private Integer skuId;

    private BigDecimal price;

    private Integer quantity;

    private Date created;

    private Date updated;

    public Cart(Integer id, Integer userId, Integer itemId, Integer skuId, BigDecimal price, Integer quantity, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.skuId = skuId;
        this.price = price;
        this.quantity = quantity;
        this.created = created;
        this.updated = updated;
    }

    public Cart() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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