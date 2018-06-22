package com.chouchongkeji.service.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoActivity;
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
}
