package com.chouchongkeji.service.user.friend.vo;

/**
 * @author linqin
 * @date 2019/1/11 16:39
 */

public class CountVo {

    private Integer commentCount;  //未查看的评论数量

    private Integer pariseCount;  //未查看的点赞数量

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getPariseCount() {
        return pariseCount;
    }

    public void setPariseCount(Integer pariseCount) {
        this.pariseCount = pariseCount;
    }
}
