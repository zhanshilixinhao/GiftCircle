package com.chouchongkeji.dial.pojo.v3;

import com.yichen.auth.jackson.ImgUrl;
import lombok.ToString;

import java.util.Date;

@ToString
public class ElectronicCoupons {
    private Integer id;

    private String title;

    private String summary;

    @ImgUrl
    private String logo;

    private String storeIds;

    private Integer adminId;

    private Byte type;

    private Byte status;

    private Date date;

    private Date updated;

    private Date created;

    public ElectronicCoupons(Integer id, String title, String summary, String logo, String storeIds, Integer adminId, Byte type, Byte status, Date date, Date updated, Date created) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.logo = logo;
        this.storeIds = storeIds;
        this.adminId = adminId;
        this.type = type;
        this.status = status;
        this.date = date;
        this.updated = updated;
        this.created = created;
    }

    public ElectronicCoupons() {
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(String storeIds) {
        this.storeIds = storeIds == null ? null : storeIds.trim();
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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