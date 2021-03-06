package com.chouchongkeji.dial.pojo.gift.article;

import java.util.Date;

public class ArticleScene {
    private Integer id;

    private String title;

    private Date created;

    private Date updated;

    public ArticleScene(Integer id, String title, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.created = created;
        this.updated = updated;
    }

    public ArticleScene() {
        super();
    }

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
        this.title = title == null ? null : title.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}