package com.chouchongkeji.service.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoActivity;
import com.chouchongkeji.dial.pojo.user.memo.MemonEvent;
import com.chouchongkeji.goexplore.common.Response;

import java.util.HashSet;

/**
 * @author yichenshanren
 * @date 2018/6/22
 */

public interface MemoService {

    /**
     * 添加活动
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    Response addActivity(Integer userId, MemoActivity activity, HashSet<Integer> isSet);

    /**
     * 修改活动信息
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    Response modifyActivity(Integer userId, MemoActivity activity, HashSet<Integer> idSet);

    /**
     * 添加事件
     *
     * @param userId
     * @param event
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    Response addEvent(Integer userId, MemonEvent event);

    /**
     * 修改事件
     *
     * @param userId 用户信息
     * @param event  事件数据
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    Response modifyEvent(Integer userId, MemonEvent event);

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
    Response getList(Integer userId, Long start, Long end);

    /**
     * 活动好友的备忘录
     *
     * @param userId 用户信息
     * @param start  开始时间
     * @param end    结束时间
     * @param friendUserId
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    Response getListForFriend(Integer userId, Long start, Long end, Integer friendUserId);

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
    Response getDetail(Integer userId, Integer id, Integer type);

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
    Response delMemo(Integer userId, Integer id, Integer type);

}
