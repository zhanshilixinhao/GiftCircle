package com.chouchongkeji.service.user.friend.impl;

import com.chouchongkeji.dial.dao.friend.FriendMapper;
import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.dao.user.LeaveMessageMapper;
import com.chouchongkeji.dial.pojo.friend.Friend;
import com.chouchongkeji.dial.pojo.gift.virtualItem.AppMessage;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.dial.pojo.user.LeaveMessage;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.message.MessageService;
import com.chouchongkeji.service.user.friend.LeaveMessageService;
import com.chouchongkeji.service.user.friend.vo.FriendVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linqin
 * @date 2019/3/20 14:42
 */
@Service
@Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)
public class LeaveMessageServiceImpl implements LeaveMessageService {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private LeaveMessageMapper leaveMessageMapper;

    @Autowired
    private MessageService messageService;

    /**
     * 给好友留言
     *
     * @param userId
     * @param friendUserId 好友id
     * @param detail       留言内容
     * @return
     * @author linqin
     * @date 2019/3/20
     */
    @Override
    public Response leaveMessage(Integer userId, Integer friendUserId, String detail) {
        // 查询用户信息
        AppUser user = appUserMapper.selectByPrimaryKey(userId);
        // 判断targetUserId是否存在
        AppUser target = appUserMapper.selectByPrimaryKey(friendUserId);
        if (target == null) {
            return ResponseFactory.err("好友用户不存在!");
        }
        // 判断是不是好友关系
        FriendVo friend = friendMapper.selectByUserIdAndFriendUserId(userId, friendUserId);
        if (friend == null) {
            return ResponseFactory.err("不是好友");
        }

        // 留言
        LeaveMessage leaveMessage = new LeaveMessage();
        leaveMessage.setUserId(userId);
        leaveMessage.setFriendUserId(friendUserId);
        leaveMessage.setDetail(detail);
       leaveMessageMapper.insert(leaveMessage);
       // 添加系统消息
        AppMessage appMessage = new AppMessage();
        appMessage.setTitle("系统通知");
        appMessage.setSummary(user.getNickname()+ "给你留言");
        appMessage.setContent(detail);
        appMessage.setTargetId(leaveMessage.getId().longValue());
        appMessage.setTargetType((byte) 27);
        appMessage.setMessageType((byte) 2);
        int in = messageService.addMessage(appMessage, new ArrayList<Integer>() {
            {
                add(friendUserId);
            }
        });
        if (in < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "添加系统消息失败");
        }
        return ResponseFactory.sucMsg("留言成功");
    }


}
