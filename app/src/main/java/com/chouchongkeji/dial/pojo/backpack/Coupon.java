package com.chouchongkeji.dial.pojo.backpack;

import java.util.Date;

public class Coupon {
    private Integer id;

    private Integer merchantId;

    private Integer adminId;

    private String title;

    private String content;

    private String cover;

    private Integer brandId;

    private Byte expire;

    private Date starting;

    private Date ending;

    private Date updated;

    private Date created;

    public Coupon(Integer id, Integer merchantId, Integer adminId, String title, String content, String cover, Integer brandId, Byte expire, Date starting, Date ending, Date updated, Date created) {
        this.id = id;
        this.merchantId = merchantId;
        this.adminId = adminId;
        this.title = title;
        this.content = content;
        this.cover = cover;
        this.brandId = brandId;
        this.expire = expire;
        this.starting = starting;
        this.ending = ending;
        this.updated = updated;
        this.created = created;
    }

    public Coupon() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Byte getExpire() {
        return expire;
    }

    public void setExpire(Byte expire) {
        this.expire = expire;
    }

    public Date getStarting() {
        return starting;
    }

    public void setStarting(Date starting) {
        this.starting = starting;
    }

    public Date getEnding() {
        return ending;
    }

    public void setEnding(Date ending) {
        this.ending = ending;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}