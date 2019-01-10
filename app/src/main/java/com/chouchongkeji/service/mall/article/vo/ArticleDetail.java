package com.chouchongkeji.service.mall.article.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.util.Date;

/**
 * 文章详情展示
 *
 * @author yy
 * @date 2018/6/11
 **/
public class ArticleDetail {
    private Integer id;//文章id
    // 文章标题
    private String title;

    @ImgUrl
    private String cover;//封面图

    // 创建时间
    private Date created;
    // 文章详情
    private String detail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

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
}
