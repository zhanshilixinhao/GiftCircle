package com.chouchongkeji.service.friend.impl;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.dial.dao.friend.FriendGroupMapper;
import com.chouchongkeji.dial.dao.friend.FriendMapper;
import com.chouchongkeji.dial.dao.friend.NewFriendNotifyMapper;
import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.dial.pojo.friend.Friend;
import com.chouchongkeji.dial.pojo.friend.FriendGroup;
import com.chouchongkeji.dial.pojo.friend.NewFriendNotify;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.service.friend.FriendService;
import com.chouchongkeji.service.friend.vo.Content;
import com.chouchongkeji.service.friend.vo.NotifyMsg;
import com.chouchongkeji.util.Constants;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/6/21
 */

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private NewFriendNotifyMapper newFriendNotifyMapper;

    @Autowired
    private FriendGroupMapper friendGroupMapper;

    /**
     * 添加好友
     *
     * @param userId        用户i西南西
     * @param targetUserId  添加的用户id
     * @param validationMsg 验证信息
     * @param remark        备注名字
     * @param groupId       分组id
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response addFriend(Integer userId, Integer targetUserId, String validationMsg, String remark, Integer groupId) {
        // 判断targetUserId是否存在
        AppUser target = appUserMapper.selectByPrimaryKey(targetUserId);
        if (target == null) {
            return ResponseFactory.err("添加的用户不存在!");
        }
        // 判断是不是好友关系
        Friend friend = friendMapper.selectByUserIdAndFriendUserId(userId, targetUserId);
        if (friend != null) {
            return ResponseFactory.err("已经是好友了");
        }
        // 如果设置了分组，判断分组是否存在
        if (groupId != null) {
            FriendGroup friendGroup = friendGroupMapper.selectByPrimaryKey(groupId);
            if (friendGroup == null) {
                return ResponseFactory.err("分组不存在，请先创建分组!");
            }
        }

        // 如果不是好友关系添加
        // 添加一条好友通知消息
        NewFriendNotify notify = new NewFriendNotify();
        notify.setUserId(userId);
        notify.setTargetUserId(targetUserId);
        // 保存添加用户时需要的内容
        Content content = new Content(groupId, remark);
        notify.setContent(JSON.toJSONString(content));
        // 验证信息
        notify.setValidationMsg(validationMsg);
        notify.setNotifyType((byte) 1);
        // 状态
        notify.setStatus(Constants.FRIEND_NOTIFY_STATUS.DEFAULT);
        // 自己的状态
        notify.setUserStatus(Constants.FRIEND_NOTIFY_STATUS.DEFAULT);
        // 目标用户的状态
        notify.setTargetUserStatus(Constants.FRIEND_NOTIFY_STATUS.NONE);
        // 添加
        newFriendNotifyMapper.insert(notify);
        return ResponseFactory.sucMsg("已发送好友申请通知");
    }


    /**
     * 获取好友验证消息
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response getNotifyMsgs(Integer userId, PageQuery page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<NotifyMsg> list = newFriendNotifyMapper.selectByUserId(userId);
        return ResponseFactory.sucData(list);
    }
}
