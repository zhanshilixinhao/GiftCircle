package com.chouchongkeji.service.user.friend;

import com.chouchongkeji.dial.pojo.user.memo.Moment;
import com.chouchongkeji.dial.pojo.user.memo.MomentComment;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

/**
 * @author yichenshanren
 * @date 2018/6/26
 */

public interface MomentService {

    /**
     * 添加一条秀秀
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    Response addMoment(Integer userId, Moment moment);

    /**
     * 赞或者取消赞
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    Response praise(Integer userId, Integer momentId);


    /**
     * 添加评论
     *
     * @param userId  用户i西南西
     * @param content
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    Response addComment(Integer userId, MomentComment comment);

    /**
     * 秀秀列表-包括好友
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    Response getList(Integer userId, PageQuery page);

    /**
     * 添加评论
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    Response addReply(Integer userId, String content, Integer commentId);
}
