package com.chouchongkeji.controller.friend;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.friend.FriendService;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
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
     * 好友泪飙
     *
     * @param userDetails 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("list")
    public Response getFriendList(@AuthenticationPrincipal UserDetails userDetails, Integer groupId) {
        return friendService.getFriendList(userDetails.getUserId(), groupId);
    }


    /**
     * 修改好友信息
     *
     * @param userDetails  用户信息
     * @param groupId      新的分组id
     * @param remark       新的备注
     * @param relationship 新的关系
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("modify")
    public Response modifyFriend(@AuthenticationPrincipal UserDetails userDetails,
                                 Integer friendUserId, Integer groupId,
                                 String remark, String relationship) {
        return friendService.modifyFriend(userDetails.getUserId(), friendUserId, groupId, remark, relationship);
    }

    /**
     * 修改好友信息
     *
     * @param userDetails 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("del")
    public Response delFriend(@AuthenticationPrincipal UserDetails userDetails,
                              Integer friendUserId) {
        return friendService.delFriend(userDetails.getUserId(), friendUserId);
    }


    /**
     * 添加分组
     *
     * @param userDetails 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("group/add")
    public Response addGroup(@AuthenticationPrincipal UserDetails userDetails, String groupName) {
        if (StringUtils.isBlank(groupName)) {
            return ResponseFactory.errMissingParameter();
        }
        return friendService.addGroup(userDetails.getUserId(), groupName);
    }

    /**
     * 删除分组
     *
     * @param userDetails 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("group/del")
    public Response delGroup(@AuthenticationPrincipal UserDetails userDetails, Integer groupId) {
        if (groupId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return friendService.delGroup(userDetails.getUserId(), groupId);
    }

    /**
     * 修改分组名称
     *
     * @param userDetails 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("group/modify")
    public Response modifyGroup(@AuthenticationPrincipal UserDetails userDetails, Integer groupId, String groupName) {
        if (groupId == null || StringUtils.isBlank(groupName)) {
            return ResponseFactory.errMissingParameter();
        }
        return friendService.modifyGroup(userDetails.getUserId(), groupId, groupName);
    }

    /**
     * 修改好友分组
     *
     * @param userDetails 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("group/move")
    public Response moveGroup(@AuthenticationPrincipal UserDetails userDetails, Integer friendUserId, Integer groupId) {
        if (groupId == null || friendUserId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return friendService.moveGroup(userDetails.getUserId(), friendUserId, groupId);
    }

    /**
     * 修改好友分组
     *
     * @param userDetails 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("group/list")
    public Response getGroupList(@AuthenticationPrincipal UserDetails userDetails) {
        return friendService.getGroupList(userDetails.getUserId());
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

    /**
     * 好友操作
     * 同意
     * 拒绝
     * 回复
     *
     * @param userDetails 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("opt")
    public Response optNotifyMsg(@AuthenticationPrincipal UserDetails userDetails, Integer msgId,
                                 Integer opt, String reply) {
        if (msgId == null || opt == null || opt < 0 || opt > 3) {
            return ResponseFactory.errMissingParameter();
        }
        if (opt == 3 && StringUtils.isBlank(reply)) {
            return ResponseFactory.err("请输入回复内容!");
        }
        return friendService.optNotifyMsg(userDetails.getUserId(), opt, msgId, reply);
    }
}
