package com.chouchongkeji.service.user.friend.vo;

import java.util.Date;

/**
 * @author yichenshanren
 * @date 2018/6/26
 */

public class CommentVo {

    private Integer commentId;
    private String content;
    private Integer momentId;
    private Byte type;

    private CommentUserVo createUser;
    private CommentUserVo targetUser;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public CommentUserVo getCreateUser() {
        return createUser;
    }

    public void setCreateUser(CommentUserVo createUser) {
        this.createUser = createUser;
    }

    public CommentUserVo getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(CommentUserVo targetUser) {
        this.targetUser = targetUser;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    private Date created;


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
}
