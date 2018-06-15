package com.chouchongkeji.service.gift.item.vo;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2018/6/14
 */
public class ItemListVo {
    private Integer itemId;

    private String cover;

    private String title;

    private BigDecimal price;


    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
