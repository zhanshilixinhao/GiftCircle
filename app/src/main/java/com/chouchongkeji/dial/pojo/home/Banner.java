package com.chouchongkeji.dial.pojo.home;

import com.yichen.auth.jackson.ImgUrl;

import java.util.Date;

public class Banner {
    private Integer id;

    private String targetid;

    @ImgUrl
    private String cover;

    private String requesturl;

    private String title;

    private String url;

    private Byte type;

    private Byte status;

    private Date createtime;

    private Date updatetime;

    private String richtext;

    public Banner(Integer id, String targetid, String cover, String requesturl, String title, String url, Byte type, Byte status, Date createtime, Date updatetime) {
        this.id = id;
        this.targetid = targetid;
        this.cover = cover;
        this.requesturl = requesturl;
        this.title = title;
        this.url = url;
        this.type = type;
        this.status = status;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Banner(Integer id, String targetid, String cover, String requesturl, String title, String url, Byte type, Byte status, Date createtime, Date updatetime, String richtext) {
        this.id = id;
        this.targetid = targetid;
        this.cover = cover;
        this.requesturl = requesturl;
        this.title = title;
        this.url = url;
        this.type = type;
        this.status = status;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.richtext = richtext;
    }

    public Banner() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTargetid() {
        return targetid;
    }

    public void setTargetid(String targetid) {
        this.targetid = targetid == null ? null : targetid.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getRequesturl() {
        return requesturl;
    }

    public void setRequesturl(String requesturl) {
        this.requesturl = requesturl == null ? null : requesturl.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getRichtext() {
        return richtext;
    }

    public void setRichtext(String richtext) {
        this.richtext = richtext == null ? null : richtext.trim();
    }
}