package com.chouchongkeji.service.mall.item.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.util.Date;
import java.util.List;

/**
 * @author yy
 * @date 2018/6/13
 **/
public class ItemCommentVo {
    // 评论时间
    private Date created;
    //用户id
    private Integer userId;
    // 用户电话
    private String phone;
    // 用户昵称
    private String nickname;
    // 用户头像
    @ImgUrl
    private String avatar;
    // 评论文字
    private String content;
    // 评论图片
    @ImgUrl
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
