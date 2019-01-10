package com.chouchongkeji.service.mall.article.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.util.Date;

/**
 * 用于礼物模块 文章列表的显示
 *
 * @author yy
 * @date 2018/6/11
 **/
public class ArticleVo {
    // 文章id
    private Integer id;
    // 文章标题
    private String title;
    // 文章简介
    private String summary;
    // 文章封面
    @ImgUrl
    private String cover;
    // 创建时间
    private Date created;
    //文章类型 1 banner 2 星座 3 普通文章
    private Byte type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
