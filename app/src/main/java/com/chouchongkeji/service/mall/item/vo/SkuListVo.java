package com.chouchongkeji.service.mall.item.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author linqin
 * @date 2018/6/18
 */
public class SkuListVo {

    private Integer skuId;

    private Integer itemId;

    private String title;

    @ImgUrl
    private String cover;

    private BigDecimal price;

    private Integer stock;

    private Integer sales;

    private List<SkuValueVo> values;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
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
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public List<SkuValueVo> getValues() {
        return values;
    }

    public void setValues(List<SkuValueVo> values) {
        this.values = values;
    }

}
