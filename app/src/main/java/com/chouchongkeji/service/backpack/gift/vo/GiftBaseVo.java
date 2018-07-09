package com.chouchongkeji.service.backpack.gift.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.util.Date;

/**
 * @author yichenshanren
 * @date 2018/7/9
 */

public class GiftBaseVo {

    private String title;
    private Integer targetId;
    private Byte targetType;

    @ImgUrl
    private String cover;
    private Date created;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
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

    public Byte getTargetType() {
        return targetType;
    }

    public void setTargetType(Byte targetType) {
        this.targetType = targetType;
    }
}
