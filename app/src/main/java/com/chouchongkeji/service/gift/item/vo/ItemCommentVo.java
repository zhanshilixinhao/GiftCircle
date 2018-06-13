package com.chouchongkeji.service.gift.item.vo;

import java.util.Date;
import java.util.List;

/**
 * @author yy
 * @date 2018/6/13
 **/
public class ItemCommentVo {
    // 评论时间
    private Date created;
    // 用户电话
    private String phone;
    // 用户头像
    private String avatar;
    // 评论文字
    private String content;
    // 评论图片
    private List<String> pictures;
    // 评价等级
    private Integer star;

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
