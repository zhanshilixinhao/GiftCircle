package com.chouchongkeji.service.user.memo.impl;

import com.chouchongkeji.dial.dao.friend.FriendMapper;
import com.chouchongkeji.dial.dao.user.memo.MemoActivityMapper;
import com.chouchongkeji.dial.dao.user.memo.MemonEventMapper;
import com.chouchongkeji.dial.pojo.friend.Friend;
import com.chouchongkeji.dial.pojo.user.memo.MemoActivity;
import com.chouchongkeji.dial.pojo.user.memo.MemonEvent;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.Utils;
import com.chouchongkeji.service.user.friend.vo.FriendVo;
import com.chouchongkeji.service.user.memo.MemoService;
import com.chouchongkeji.service.user.memo.vo.MemoItemVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * 备忘录
 *
 * @author yichenshanren
 * @date 2018/6/22
 */
@Service
public class MemoServiceImpl implements MemoService {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private MemoActivityMapper memoActivityMapper;

    @Autowired
    private MemonEventMapper memonEventMapper;

    /**
     * 添加活动
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @Override
    public Response addActivity(Integer userId, MemoActivity activity, HashSet<Integer> isSet) {
        // 判断被邀请的用户是不是好友关系
        FriendVo friend;
        for (Integer id : isSet) {
            friend = friendMapper.selectByUserIdAndFriendUserId(userId, id);
            if (friend == null) {
                return ResponseFactory.err(String.format("你和用户%s的用户不是好友关系，不能邀请Ta", id));
            }
        }
        // 添加活动
        activity.setUserId(userId);
        memoActivityMapper.insert(activity);
        return ResponseFactory.sucMsg("添加成功!");
    }

    /**
     * 修改活动信息
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @Override
    public Response modifyActivity(Integer userId, MemoActivity activity, HashSet<Integer> idSet) {
        // 判断用户是否有修改的权限
        MemoActivity old = memoActivityMapper.selectByPrimaryKey(activity.getId());
        if (old == null) {
            return ResponseFactory.err("修改的活动不存在!");
        }
        // 判权限
        if (old.getUserId().compareTo(userId) != 0) {
            return ResponseFactory.err("无权修改!");
        }
        // 如过修改了被邀请的用户
        if (CollectionUtils.isNotEmpty(idSet)) {
            // 判断被邀请的用户是不是好友关系
            FriendVo friend;
            for (Integer id : idSet) {
                friend = friendMapper.selectByUserIdAndFriendUserId(userId, id);
                if (friend == null) {
                    return ResponseFactory.err(String.format("你和用户id为%s的用户不是好友关系，不能邀请Ta", id));
                }
            }
        }
        // 修改活动信息
        activity.setUserId(null);
        memoActivityMapper.updateByPrimaryKeySelective(activity);
        return ResponseFactory.sucMsg("修改成功!");
    }

    /**
     * 添加事件
     *
     * @param userId
     * @param event
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @Override
    public Response addEvent(Integer userId, MemonEvent event) {
        event.setUserId(userId);
        memonEventMapper.insert(event);
        return ResponseFactory.sucMsg("添加成功!");
    }

    /**
     * 修改事件
     *
     * @param userId 用户信息
     * @param event  事件数据
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @Override
    public Response modifyEvent(Integer userId, MemonEvent event) {
        event.setUserId(null);
        memonEventMapper.updateByPrimaryKeySelective(event);
        return ResponseFactory.sucMsg("修改成功!");
    }

    /**
     * 添加活动
     *
     * @param userId 用户信息
     * @param start  开始时间
     * @param end    结束时间
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */

    @Override
    public Response getList(Integer userId, Long start, Long end) {
        List<MemoItemVo> list = memoActivityMapper.selectByUserIdAndDate(userId, start, end);
        return ResponseFactory.sucData(list);
    }

    /**
     * 活动好友的备忘录
     *
     * @param userId       用户信息
     * @param start        开始时间
     * @param end          结束时间
     * @param friendUserId
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @Override
    public Response getListForFriend(Integer userId, Long start, Long end, Integer friendUserId) {
        // 判断是不是好友关系
        FriendVo friend = friendMapper.selectByUserIdAndFriendUserId(userId, friendUserId);
        if (friend == null) {
            return ResponseFactory.err("需要添加好友才能查看!");
        }
        List<MemoItemVo> list = memonEventMapper
                .selectByUserIdAndDate(friendUserId, start, end);
        return ResponseFactory.sucData(list);
    }

    /**
     * 添加活动
     *
     * @param userId 用户信息
     * @param id     备忘录 活动 | 事件 id
     * @param type   类型 1 活动 2 事件
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @Override
    public Response getDetail(Integer userId, Integer id, Integer type) {
        // 查询活动
        if (type == 1) {
            MemoActivity activity = memoActivityMapper.selectByPrimaryKey(id);
            if (activity == null) {
                return ResponseFactory.err("活动不存在!");
            }
            if (activity.getUserId().equals(userId)) {
                return ResponseFactory.sucData(activity);
            }
            String str = activity.getUsers();
            HashSet<Integer> idSet = new HashSet<>();
            Utils.getIds(str, idSet);
            // 判断是不是被邀请的用户
            if (idSet.contains(userId)) {
                return ResponseFactory.sucData(activity);
            }
            return ResponseFactory.err("无权查看该活动!");
        }
        // 查询事件详情
        MemonEvent event = memonEventMapper.selectByPrimaryKey(id);
        if (event == null) {
            return ResponseFactory.err("事件不存在!");
        }
        if (event.getUserId().compareTo(userId) == 0) {
            return ResponseFactory.sucData(event);
        }
        return ResponseFactory.err("无权查看该事件!");
    }

    /**
     * 删除一个备忘录信息
     *
     * @param userId 用户信息
     * @param id     备忘录 活动 | 事件 id
     * @param type   类型 1 活动 2 事件
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @Override
    public Response delMemo(Integer userId, Integer id, Integer type) {
        // 删除活动
        int count;
        if (type == 1) {
            count = memoActivityMapper.deleteByPrimaryKeyAndUserId(id, userId);
        } else {
            // 删除事件
            count = memonEventMapper.deleteByPrimaryKeyAndUserId(id, userId);
        }
        if (count > 0) {
            return ResponseFactory.sucMsg("删除成功!");
        }
        return ResponseFactory.err("删除失败!");
    }
}
