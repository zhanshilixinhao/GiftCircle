package com.chouchongkeji.service.gift.favorite.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;

/**
 * @author yy
 * @date 2018/6/13
 **/
public class FavoriteListVo {
    //  用户收藏id
    private Integer id;
    //  商品id
    private Integer itemId;
    //  商品标题
    private String title;
    //  商品封面
    @ImgUrl
    private String cover;
    //  商品价格
    private BigDecimal price;

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
}
