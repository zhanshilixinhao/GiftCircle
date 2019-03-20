package com.chouchongkeji.service.mall.item.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2019/2/14 15:22
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ItemArticleListVo {

    private Integer id; //商品/文章id/事件id

    @ImgUrl
    private String cover;  //图片

    private String title; //标题

    private BigDecimal price; //商品价格

    private String summary; //文章简介

    private Integer type; // 1 商品，2 文章，3备忘录事件

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
