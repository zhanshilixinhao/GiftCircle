package com.chouchongkeji.controller.friend;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.friend.FriendService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yichenshanren
 * @date 2018/6/21
 */

@RestController
@RequestMapping("auth/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    /**
     * 添加好友
     *
     * @param userDetails   用户i西南西
     * @param targetUserId  添加的用户id
     * @param validationMsg 验证信息
     * @param remark        备注名字
     * @param groupId       分组id
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("add")
    public Response addFriend(@AuthenticationPrincipal UserDetails userDetails,
                              Integer targetUserId,
                              String validationMsg, String remark, Integer groupId) {
        if (targetUserId == null) return ResponseFactory.errMissingParameter();
        if (userDetails.getUserId().compareTo(targetUserId) == 0) {
            return ResponseFactory.err("不能添加自己为好友!");
        }
        return friendService.addFriend(userDetails.getUserId(),
                targetUserId, validationMsg, remark, groupId);
    }


    /**
     * 获取好友验证消息
     *
     * @param userDetails 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("notifyMsgs")
    public Response getNotifyMsgs(@AuthenticationPrincipal UserDetails userDetails, PageQuery page) {
        return friendService.getNotifyMsgs(userDetails.getUserId(), page);
    }
}
