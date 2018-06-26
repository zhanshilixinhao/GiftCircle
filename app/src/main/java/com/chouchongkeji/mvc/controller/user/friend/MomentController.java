package com.chouchongkeji.mvc.controller.user.friend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.dial.pojo.user.memo.Moment;
import com.chouchongkeji.dial.pojo.user.memo.MomentComment;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.user.friend.MomentService;
import com.chouchongkeji.service.user.friend.vo.MediaVo;
import com.chouchongkeji.util.Constants;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 秀秀
 *
 * @author yichenshanren
 * @date 2018/6/26
 */

@RestController
@RequestMapping("auth/moment")
public class MomentController {

    @Autowired
    private MomentService momentService;

    /**
     * 添加一条秀秀
     *
     * @param userDetails 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    @PostMapping("publish")
    public Response addMoment(@AuthenticationPrincipal UserDetails userDetails,
                              Moment moment) {
        if (StringUtils.isBlank(moment.getContent())) {
            return ResponseFactory.errMissingParameter();
        }
        // 如果带着图片或是视频判断格式是否正确
        if (StringUtils.isNotBlank(moment.getMedias())) {
            List<MediaVo> list = JSON.parseObject(moment.getMedias(), new TypeReference<List<MediaVo>>() {
            });
            for (MediaVo mediaVo : list) {
                if (mediaVo.getType() == null || mediaVo.getType() < 1 || mediaVo.getType() > 2
                        || StringUtils.isBlank(mediaVo.getUrl())) {
                    return ResponseFactory.err("medias数据错误!");
                }
            }
        }
        // 默认不显示收到的礼物
        Byte showGift = moment.getShowGift();
        if (showGift == null || showGift < 1 || showGift > 2) {
            showGift = 2;
            moment.setShowGift(showGift);
        }
        return momentService.addMoment(userDetails.getUserId(), moment);
    }


    /**
     * 赞或者取消赞
     *
     * @param userDetails 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    @PostMapping("praise")
    public Response praise(@AuthenticationPrincipal UserDetails userDetails, Integer momentId) {
        if (momentId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return momentService.praise(userDetails.getUserId(), momentId);
    }

    /**
     * 添加评论
     *
     * @param userDetails 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    @PostMapping("comment")
    public Response addComment(@AuthenticationPrincipal UserDetails userDetails, MomentComment comment) {
        if (comment.getMomentId() == null || StringUtils.isBlank(comment.getContent())) {
            return ResponseFactory.errMissingParameter();
        }
        comment.setType(Constants.MOMENT_COMMENT_TYPE.COMMENT);
        comment.setTargetUserId(null);
        return momentService.addComment(userDetails.getUserId(), comment);
    }

    /**
     * 添加评论
     *
     * @param userDetails 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    @PostMapping("reply")
    public Response addReply(@AuthenticationPrincipal UserDetails userDetails,
                             String content, Integer commentId) {
        if (
                StringUtils.isBlank(content)
                        || commentId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return momentService.addReply(userDetails.getUserId(), content, commentId);
    }

    /**
     * 秀秀列表-包括好友
     *
     * @param userDetails 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/26
     */
    @PostMapping("list")
    public Response getList(@AuthenticationPrincipal UserDetails userDetails, PageQuery page) {
        return momentService.getList(userDetails.getUserId(), page);
    }
}
