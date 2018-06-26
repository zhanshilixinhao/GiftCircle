package com.chouchongkeji.dial.pojo.user.memo;

import java.util.Date;

public class MomentComment {
    private Integer id;

    private Integer tCommentId;

    private Integer momentId;

    private Integer userId;

    private Integer targetUserId;

    private String content;

    private Byte type;

    private Date created;

    private Date updated;

    public MomentComment(Integer id, Integer tCommentId, Integer momentId, Integer userId, Integer targetUserId, String content, Byte type, Date created, Date updated) {
        this.id = id;
        this.tCommentId = tCommentId;
        this.momentId = momentId;
        this.userId = userId;
        this.targetUserId = targetUserId;
        this.content = content;
        this.type = type;
        this.created = created;
        this.updated = updated;
    }

    public MomentComment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer gettCommentId() {
        return tCommentId;
    }

    public void settCommentId(Integer tCommentId) {
        this.tCommentId = tCommentId;
    }

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Integer targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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