package com.chouchongkeji.dial.pojo.user.memo;

import java.util.Date;

public class Moment {
    private Integer id;

    private Integer userId;

    private String content;

    private String medias;

    private Byte showGift;

    private Integer mediaCount;

    private Date created;

    private Date updated;

    public Moment(Integer id, Integer userId, String content, String medias, Byte showGift, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.medias = medias;
        this.showGift = showGift;
        this.created = created;
        this.updated = updated;
    }

    public Moment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMedias() {
        return medias;
    }

    public void setMedias(String medias) {
        this.medias = medias == null ? null : medias.trim();
    }

    public Byte getShowGift() {
        return showGift;
    }

    public void setShowGift(Byte showGift) {
        this.showGift = showGift;
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

    public Integer getMediaCount() {
        return mediaCount;
    }

    public void setMediaCount(Integer mediaCount) {
        this.mediaCount = mediaCount;
    }
}