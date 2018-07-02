package com.chouchongkeji.dial.pojo.backpack.consignment;

import java.math.BigDecimal;
import java.util.Date;

public class Consignment {
    private Integer id;

    private Long bpId;

    private Integer userId;

    private Integer targetId;

    private Integer quantity;

    private BigDecimal price;

    private Byte type;

    private Byte status;

    private Date updated;

    private Date created;

    public Consignment(Integer id, Long bpId, Integer userId, Integer targetId, Integer quantity, BigDecimal price, Byte type, Byte status, Date updated, Date created) {
        this.id = id;
        this.bpId = bpId;
        this.userId = userId;
        this.targetId = targetId;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
        this.status = status;
        this.updated = updated;
        this.created = created;
    }

    public Consignment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}