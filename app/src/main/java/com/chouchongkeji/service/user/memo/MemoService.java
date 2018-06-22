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
}
