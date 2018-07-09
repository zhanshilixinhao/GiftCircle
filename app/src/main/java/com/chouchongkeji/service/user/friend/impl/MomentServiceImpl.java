package com.chouchongkeji.service.user.friend.impl;

import com.chouchongkeji.dial.dao.user.memo.MomentCommentMapper;
import com.chouchongkeji.dial.dao.user.memo.MomentMapper;
import com.chouchongkeji.dial.dao.user.memo.MomentPraiseMapper;
import com.chouchongkeji.dial.pojo.user.memo.Moment;
import com.chouchongkeji.dial.pojo.user.memo.MomentComment;
import com.chouchongkeji.dial.pojo.user.memo.MomentPraise;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.gift.GiftService;
import com.chouchongkeji.service.user.friend.FriendService;
import com.chouchongkeji.service.user.friend.MomentService;
import com.chouchongkeji.service.user.friend.vo.MomentVo;
import com.chouchongkeji.util.Constants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/6/26
 */
@Service
public class MomentServiceImpl implements MomentService {

    @Autowired
    private MomentMapper momentMapper;

    @Autowired
    private MomentCommentMapper momentCommentMapper;

    @Autowired
    private MomentPraiseMapper momentPraiseMapper;

    @Autowired
    private FriendService friendService;

    @Autowired
    private GiftService giftService;

    /**
     * 添加一条秀秀
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    @Override
    public Response addMoment(Integer userId, Moment moment) {
        moment.setUserId(userId);
        momentMapper.insert(moment);
        return ResponseFactory.sucMsg("发布成功!");
    }

    /**
     * 赞或者取消赞
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    @Override
    public Response praise(Integer userId, Integer momentId) {// 查询秀秀
        Moment moment = momentMapper.selectByPrimaryKey(momentId);
        if (moment == null) {
            return ResponseFactory.err("不存在的id");
        }
        // 判断是不是好友关系
        if (userId.compareTo(moment.getUserId()) != 0 && friendService.isFriend(userId, moment.getUserId()) == null) {
            return ResponseFactory.err("添加好友才能赞或取消赞");
        }
        int re = 0;
        // 判断有没有攒过
        MomentPraise praise = momentPraiseMapper.selectByMomentIdAndUserId(momentId, userId);
        if (praise == null) {
            praise = new MomentPraise();
            praise.setMomentId(momentId);
            praise.setUserId(userId);
            momentPraiseMapper.insert(praise);
            re = 1;
        } else {
            momentPraiseMapper.deleteByPrimaryKey(praise.getId());
            re = 2;
        }
        return ResponseFactory.sucData(re);
    }


    /**
     * 添加评论
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    @Override
    public Response addComment(Integer userId, MomentComment comment) {
        Moment moment = momentMapper.selectByPrimaryKey(comment.getMomentId());
        if (moment == null) {
            return ResponseFactory.err("不存在!");
        }
        // 判断是不是好友关系
        if (userId.compareTo(moment.getUserId()) != 0 && friendService.isFriend(userId, moment.getUserId()) == null) {
            return ResponseFactory.err("添加好友才能评论");
        }
        // 添加评论
        comment.setUserId(userId);
        momentCommentMapper.insert(comment);
        return ResponseFactory.suc();
    }

    /**
     * 添加评论
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    @Override
    public Response addReply(Integer userId, String content, Integer commentId) {
        // 判断回复的评论是否存在
        MomentComment comment = momentCommentMapper.selectByPrimaryKey(commentId);
        if (comment == null) {
            return ResponseFactory.err("回复的评论不存在!");
        }
        // 创建心得评论
        MomentComment ncomment = new MomentComment();
        ncomment.setTargetUserId(comment.getUserId());
        ncomment.settCommentId(comment.getId());
        ncomment.setContent(content);
        ncomment.setMomentId(comment.getMomentId());
        ncomment.setType(Constants.MOMENT_COMMENT_TYPE.COMMENT);
        ncomment.setUserId(userId);
        momentCommentMapper.insert(ncomment);
        return ResponseFactory.suc();
    }

    /**
     * 秀秀列表-包括好友
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    @Override
    public Response getList(Integer userId, PageQuery page) {
        List<MomentVo> list = momentMapper.selectAll(userId, page);
        return ResponseFactory.sucData(appendRecentGifts(list));
    }

    /**
     * 添加最近30十天收到的礼物
     *
     * @param list 修胥列表
     * @return
     */
    private Object appendRecentGifts(List<MomentVo> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            for (MomentVo vo : list) {
                if (vo.getShowGift() == 1) {
                    vo.setGifts(giftService.getWithDays(vo.getCreateUserId(), DateUtils.addDays(new Date(), -30)));
                }
            }
        }
        return list;
    }

    /**
     * 获取详情
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    @Override
    public Response getDetail(Integer userId, Integer momentId) {
        MomentVo vo = momentMapper.selectDetailById(userId, momentId);
        if (vo.getShowGift() == 1) {
            vo.setGifts(giftService.getWithDays(vo.getCreateUserId(), DateUtils.addDays(new Date(), -30)));
        }
        return ResponseFactory.sucData(vo);
    }

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
    @Override
    public Response getListForSelf(Integer userId, Integer targetUserId, PageQuery page) {
        List<MomentVo> list = momentMapper.selectAllByTargetUser(userId, targetUserId, page);
        return ResponseFactory.sucData(appendRecentGifts(list));
    }

    /**
     * 删除评论
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    @Override
    public Response delComment(Integer userId, Integer commentId) {
        // 判断评论是否存在
        MomentComment comment = momentCommentMapper.selectByPrimaryKey(commentId);
        if (comment == null) {
            return ResponseFactory.err("删除的评论不存在!");
        }
        // 判断是不是我创建的
        boolean re = true;
        // 如果不是我创建的
        if (userId.compareTo(comment.getUserId()) != 0) {
            // 判断秀秀是不是我创建的
            Moment moment = momentMapper.selectByPrimaryKey(comment.getMomentId());
            // 如果秀秀存在且不是我创建的
            if (moment != null && moment.getUserId().compareTo(userId) != 0) {
                re = false;
            }
        }
        if (!re) {
            return ResponseFactory.err("无权操作!");
        }
        // 删除评论
        momentCommentMapper.deleteByPrimaryKey(commentId);
        return ResponseFactory.sucMsg("删除成功!");
    }

    /**
     * 删除秀秀
     *
     * @param userId   用户id
     * @param momentId 西秀id
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    @Override
    public Response delMoment(Integer userId, Integer momentId) {
        Moment moment = momentMapper.selectByPrimaryKey(momentId);
        if (moment == null) {
            return ResponseFactory.err("数据不存在!");
        }
        if (userId.compareTo(moment.getUserId()) != 0) {
            return ResponseFactory.err("无权操作!");
        }
        // 删除秀秀
        momentMapper.deleteByPrimaryKey(momentId);
        momentCommentMapper.deleteByMomentId(momentId);
        momentPraiseMapper.deleteByMomentId(momentId);
        return ResponseFactory.sucMsg("删除成功!");
    }
}
