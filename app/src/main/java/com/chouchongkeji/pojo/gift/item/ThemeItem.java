package com.chouchongkeji.pojo.gift.item;

import java.util.Date;

public class ThemeItem {
    private Integer id;

    private Integer itemId;

    private Integer themeId;

    private Integer sort;

    private Date created;

    private Date updated;

    public ThemeItem(Integer id, Integer itemId, Integer themeId, Integer sort, Date created, Date updated) {
        this.id = id;
        this.itemId = itemId;
        this.themeId = themeId;
        this.sort = sort;
        this.created = created;
        this.updated = updated;
    }

    public ThemeItem() {
        super();
    }

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

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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