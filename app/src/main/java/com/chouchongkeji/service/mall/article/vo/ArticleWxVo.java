package com.chouchongkeji.service.mall.article.vo;

import com.yichen.auth.jackson.ImgUrl;

/**
 * @author linqin
 * @date 2019/6/27
 */

public class ArticleWxVo {

    // 文章id
    private Integer id;
    // 文章标题
    private String title;
    // 文章简介
    private String summary;
    // 文章封面
    @ImgUrl
    private String cover;

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
}
