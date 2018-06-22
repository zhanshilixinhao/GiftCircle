package com.chouchongkeji.service.user.friend.impl;

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
import com.chouchongkeji.service.user.friend.FriendService;
import com.chouchongkeji.service.user.friend.vo.Content;
import com.chouchongkeji.service.user.friend.vo.FriendBase;
import com.chouchongkeji.service.user.friend.vo.FriendItem;
import com.chouchongkeji.service.user.friend.vo.NotifyMsg;
import com.chouchongkeji.util.Constants;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/6/21
 */

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
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
     * 删除好友
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response delFriend(Integer userId, Integer friendUserId) {
        // 判断我和该用户是不是好友关系
        Friend friend = friendMapper.selectByUserIdAndFriendUserId(userId, friendUserId);
        if (friend == null) {
            return ResponseFactory.err("不是好友关系,不用删除!");
        }
        friendMapper.deleteByUserIdAndFriendUserId(userId, friendUserId);
        return ResponseFactory.sucMsg("删除成功!");
    }

    /**
     * 搜索好友
     *
     * @param userId 用户id
     * @param key    搜索关键字-此处目前支支持手机号
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response searchFriend(Integer userId, String key) {
        AppUser user = appUserMapper.selectByPhone(key);
        if (user == null) {
            return ResponseFactory.suc();
        }
        // 判断是不是好友关系
        Friend friend = friendMapper.selectByUserIdAndFriendUserId(userId, user.getId());
        FriendBase friendBase = new FriendBase();
        friendBase.setUserId(user.getId());
        friendBase.setAvatar(user.getAvatar());
        friendBase.setNickname(user.getNickname());
        friendBase.setIsFriend(friend == null ? 2 : 1);
        return ResponseFactory.sucData(friendBase);
    }

    /**
     * 获取好友列表
     *
     * @param userId  用户id
     * @param groupId
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response getFriendList(Integer userId, Integer groupId) {
        List<FriendItem> list = friendMapper.selectByUserIdAndGroupId(userId, groupId);
        return ResponseFactory.sucData(list);
    }

    /**
     * 修改好友信息
     *
     * @param userId       用户信息
     * @param groupId      新的分组id
     * @param remark       新的备注
     * @param relationship 新的关系
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response modifyFriend(Integer userId, Integer friendUserId, Integer groupId, String remark, String relationship) {
        // 判断我和该用户是不是好友关系
        Friend friend = friendMapper.selectByUserIdAndFriendUserId(userId, friendUserId);
        if (friend == null) {
            return ResponseFactory.err("不是好友关系");
        }
        // 更新信息
        Friend friend1 = new Friend();
        friend1.setId(friend.getId());
        // 如果要修改分组
        if (groupId != null) {
            // 判断分组是否相同
            if (groupId.compareTo(friend.getGroupId()) == 0) {
                return ResponseFactory.sucMsg("成功!");
            }
            // 判断新的分组是否存在
            FriendGroup group = friendGroupMapper.selectByPrimaryKey(groupId);
            if (group == null) {
                return ResponseFactory.err("分组不存在!");
            }
            friend1.setGroupId(groupId);
        }
        // 修改备注
        if (StringUtils.isNotBlank(remark) && !StringUtils.equals(friend.getRemark(), remark)) {
            friend1.setRemark(remark);
        }
        // 修改关系
        if (StringUtils.isNotBlank(relationship) && !StringUtils.equals(friend.getRelationship(), relationship)) {
            friend1.setRelationship(relationship);
        }
        friendMapper.updateByPrimaryKeySelective(friend1);
        return ResponseFactory.sucMsg("修改成功!");
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

    /*-------------------------------------------------好友申请操作----------------------------------------------*/

    /**
     * 好友操作
     * 同意
     * 拒绝
     * 回复
     *
     * @param userId 用id
     * @param msgId
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response optNotifyMsg(Integer userId, Integer opt, Integer msgId, String reply) {
        // 判断消息是否存在
        NewFriendNotify notify = newFriendNotifyMapper.selectByPrimaryKey(msgId);
        if (notify == null) {
            return ResponseFactory.err("消息不存在!");
        }
        // 判断用户是狗能处理该消息
        // 只能处理别人请求加我的消息
        if (userId.compareTo(notify.getTargetUserId()) != 0) {
            return ResponseFactory.err("无权操作!");
        }
        // 判断消息是否已经处理过
        if (notify.getStatus() != Constants.FRIEND_NOTIFY_STATUS.DEFAULT) {
            return ResponseFactory.err("该消息已处理!");
        }
        // 处理消息
        if (opt == 1) {
            // 同意申请，添加好友关系
            agreeFriendApply(notify);
        }
        opt += 1;
        // 更新消息
        NewFriendNotify nNotify = new NewFriendNotify();
        nNotify.setId(notify.getId());
        nNotify.setStatus(opt.byteValue());
        nNotify.setTargetUserStatus(opt.byteValue());
        if (opt == 4) {
            nNotify.setReply(reply);
        }
        // 更新状态
        newFriendNotifyMapper.updateByPrimaryKeySelective(nNotify);
        return ResponseFactory.sucMsg("操作成功!");
    }


    /**
     * 同意好友申请
     *
     * @param notify 通知消息
     * @author yichen
     * @date 2018/6/21
     */
    private void agreeFriendApply(NewFriendNotify notify) {
        // 添加我和对方的好友关系
        Friend friend1 = new Friend();
        friend1.setUserId(notify.getTargetUserId());
        friend1.setFriendUserId(notify.getUserId());
        friend1.setGroupId(0);
        friend1.setSort(0);
        // 保存
        friendMapper.insert(friend1);
        // 添加对方和我的好友关系
        friend1 = new Friend();
        friend1.setFriendUserId(notify.getTargetUserId());
        friend1.setUserId(notify.getUserId());
        friend1.setSort(0);
        // 取出添加好友时的那啥
        Content content = JSON.parseObject(notify.getContent(), Content.class);
        if (content != null) {
            friend1.setGroupId(content.getGroupId() == null ? 0 : content.getGroupId());
            friend1.setRemark(content.getRemark());
        }
        friendMapper.insert(friend1);
    }

    /*-------------------------------------------------好友申请操作结束----------------------------------------------*/

    /*-------------------------------------------------好友分组操作----------------------------------------------*/

    /**
     * 添加分组
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response addGroup(Integer userId, String groupName) {
        // 判断分组名称是否重复
        FriendGroup group = friendGroupMapper.selectByUserIdAndGroupName(userId, groupName);
        if (group != null) {
            return ResponseFactory.err("分组名称重复!");
        }
        // 创建分组
        group = new FriendGroup();
        group.setName(groupName);
        group.setUserId(userId);
        group.setSort(0);
        friendGroupMapper.insert(group);
        return ResponseFactory.sucData(group);
    }

    /**
     * 删除分组
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response delGroup(Integer userId, Integer groupId) {
        int count = friendGroupMapper.deleteByPrimaryKeyAndUserId(groupId, userId);
        // 如果删除成功
        if (count == 1) {
            // 将之前该分组之下的用户移到默认分组
            friendMapper.updateMoveToGroup(userId, groupId, 0);
            return ResponseFactory.sucMsg("删除成功!");
        } else return ResponseFactory.err("删除失败!");
    }

    /**
     * 修改分组名称
     *
     * @param userId    用户信息
     * @param groupName
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response modifyGroup(Integer userId, Integer groupId, String groupName) {
        // 判断分组是否存在
        FriendGroup group = friendGroupMapper.selectByPrimaryKey(groupId);
        if (group == null) {
            return ResponseFactory.err("分组不存在!");
        }
        // 如果不是该用户的分组，不能修改
        if (userId.compareTo(group.getUserId()) != 0) {
            return ResponseFactory.err("无权修改!");
        }
        // 判断该用户是否存在同名的分组
        group = friendGroupMapper.selectByUserIdAndGroupName(userId, groupName);
        if (group != null) {
            return ResponseFactory.err("分组名称重复!");
        }
        group = new FriendGroup();
        group.setId(groupId);
        group.setName(groupName);
        friendGroupMapper.updateByPrimaryKeySelective(group);
        return ResponseFactory.sucMsg("修改成功!");
    }

    /**
     * 修改好友分组
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response moveGroup(Integer userId, Integer friendUserId, Integer groupId) {
        // 判断我和该用户是不是好友关系
        Friend friend = friendMapper.selectByUserIdAndFriendUserId(userId, friendUserId);
        if (friend == null) {
            return ResponseFactory.err("不是好友关系");
        }
        // 判断分组是否相同
        if (groupId.compareTo(friend.getGroupId()) == 0) {
            return ResponseFactory.sucMsg("成功!");
        }
        // 判断新的分组是否存在
        FriendGroup group = friendGroupMapper.selectByPrimaryKey(groupId);
        if (group == null) {
            return ResponseFactory.err("分组不存在!");
        }
        // 更新分组信息
        Friend friend1 = new Friend();
        friend1.setId(friend.getId());
        friend1.setGroupId(groupId);
        friendMapper.updateByPrimaryKeySelective(friend1);
        return ResponseFactory.sucMsg("修改成功!");
    }

    /**
     * 获取用户的分组列表
     *
     * @param userId 用户id
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response getGroupList(Integer userId) {
        List<FriendGroup> friendGroups = friendGroupMapper.selectByUserId(userId);
        if (friendGroups == null) {
            friendGroups = new ArrayList<>();
        }
        FriendGroup group = new FriendGroup();
        group.setId(0);
        group.setName(Constants.GROUP_DEFAULT_NAME);
        group.setSort(0);
        group.setUserId(userId);
        friendGroups.add(0, group);
        return ResponseFactory.sucData(friendGroups);
    }
    /*-------------------------------------------------好友分组操作结束----------------------------------------------*/


}
