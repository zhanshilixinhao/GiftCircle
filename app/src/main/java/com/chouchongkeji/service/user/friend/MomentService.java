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
     * @param type
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    Response getList(Integer userId, Integer type, PageQuery page);

    /**
     * 添加评论
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    Response addReply(Integer userId, String content, Integer commentId);

    /**
     * 获取详情
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    Response getDetail(Integer userId, Integer momentId);

    /**
     * 查看某个用户得秀秀
     *
     * @param userId
     * @param targetUserId
     * @param page
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    Response getListForSelf(Integer userId, Integer targetUserId, PageQuery page);

    /**
     * 删除评论
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    Response delComment(Integer userId, Integer commentId);

    /**
     * 删除秀秀
     *
     * @param userId   用户id
     * @param momentId 评论id
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    Response delMoment(Integer userId, Integer momentId);

    /**
     * 获取点赞/评论未查看数量
     *
     * @param
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    Response getCommentPraise(Integer userId);
}
