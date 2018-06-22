package com.chouchongkeji.service.user.memo.impl;

import com.chouchongkeji.dial.dao.friend.FriendMapper;
import com.chouchongkeji.dial.dao.user.memo.MemoActivityMapper;
import com.chouchongkeji.dial.dao.user.memo.MemonEventMapper;
import com.chouchongkeji.dial.pojo.friend.Friend;
import com.chouchongkeji.dial.pojo.user.memo.MemoActivity;
import com.chouchongkeji.dial.pojo.user.memo.MemonEvent;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.user.memo.MemoService;
import com.chouchongkeji.service.user.memo.vo.MemoItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     * @param userId     用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @Override
    public Response addActivity(Integer userId, MemoActivity activity, HashSet<Integer> isSet) {
        // 判断被邀请的用户是不是好友关系
        Friend friend;
        for (Integer id : isSet) {
            friend = friendMapper.selectByUserIdAndFriendUserId(userId, id);
            if (friend == null) {
                return ResponseFactory.err(String.format("你和用户id为%s的用户不是好友关系，不能邀请Ta", id));
            }
        }
        // 添加活动
        activity.setUserId(userId);
        memoActivityMapper.insert(activity);
        return ResponseFactory.sucMsg("添加成功!");
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
}
