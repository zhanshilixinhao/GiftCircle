package com.chouchongkeji.service.user.info;

import com.chouchongkeji.goexplore.common.Response;
import com.yichen.auth.service.UserDetails;

import java.util.HashSet;

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
     * @param tagIds       标签id
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response addTag(Integer userId, Integer friendUserId, HashSet<Integer> tagIds);

    /**
     * 获取礼物偏好列表
     *
     * @param userId 用户id
     * @param targetUserId
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response getGiftPreference(Integer userId, Integer targetUserId);

    /**
     * 添加礼物偏好
     *
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response addGiftPreference(Integer userId, HashSet<Integer> idSet);

    /**
     * 用户给好友添加的所有印象标签
     *
     * @param friendUserId
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response userTagList(Integer userId, Integer friendUserId);
}
