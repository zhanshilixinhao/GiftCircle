package com.chouchongkeji.service.gift.item.vo;

/**
 * @author yy
 * @date 2018/6/20
 **/
public class ThemeVo {
    // 主题id
    private Integer id;
    // 主题名称
    private String name;
    // 主题封面
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
