package com.chouchongkeji.dial.pojo.backpack;

import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linqin
 * @date 2018/7/2
 */
public class Vbp {
    private Long id;

    private Integer userId;

    private Integer targetId;

    private BigDecimal price;

    private String title;

    @ImgUrl
    private String cover;

    private String description;

    private String brand;

    private String spec;

    private Byte type;

    private Integer quantity;

    private Long pickRemainTime;

    private Date created;
    private Date buyTime;

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

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Long getPickRemainTime() {
        return pickRemainTime;
    }

    public void setPickRemainTime(Long pickRemainTime) {
        this.pickRemainTime = pickRemainTime;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
