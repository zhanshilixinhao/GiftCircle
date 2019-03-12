package com.chouchongkeji.service.user.memo.vo;

import com.chouchongkeji.service.mall.item.vo.ItemListVo;
import com.yichen.auth.jackson.ImgUrl;

import java.util.Date;

/**
 * 节日事件详情
 * @author linqin
 * @date 2019/3/12 16:01
 */

public class MemoFestivalVo {

    private Integer id; //节日事件id

    //详情封面
    @ImgUrl
    private String picture;

    private String title; //详情标题

    private Date targetTime;// 备忘时间

    private String detail; //详情描述

    private Date created; //创建事件

    private String yi; //适宜

    private String ji;//忌讳


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Date targetTime) {
        this.targetTime = targetTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getYi() {
        return yi;
    }

    public void setYi(String yi) {
        this.yi = yi;
    }

    public String getJi() {
        return ji;
    }

    public void setJi(String ji) {
        this.ji = ji;
    }


}
