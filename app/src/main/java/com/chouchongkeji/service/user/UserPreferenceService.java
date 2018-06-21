package com.chouchongkeji.service.user;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author yichenshanren
 * @date 2018/6/21
 */

public interface UserPreferenceService {

    /**
     * 获取标签列表
     *
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response getTags();

    /**
     * 给好友添加一个标签
     *
     * @param userId       用户id
     * @param friendUserId 好友用户id
     * @param tagId        标签id
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response addTag(Integer userId, Integer friendUserId, Integer tagId);
}
