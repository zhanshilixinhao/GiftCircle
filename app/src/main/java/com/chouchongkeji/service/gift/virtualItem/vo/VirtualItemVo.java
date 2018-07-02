package com.chouchongkeji.service.gift.virtualItem.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;

/**
 * 用于显示虚拟商品列表
 *
 * @author yy
 * @date 2018/6/11
 **/
public class VirtualItemVo {
    // 虚拟商品id
    private Integer id;
    // 虚拟商品名称
    private String name;
    // 虚拟商品品牌名称
    private String brandName;
    // 虚拟商品价格
    private BigDecimal price;
    // 虚拟商品描述
    private String description;
    // 虚拟商品封面
    @ImgUrl
    private String cover;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
