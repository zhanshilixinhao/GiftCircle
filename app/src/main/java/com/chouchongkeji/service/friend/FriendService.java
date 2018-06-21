package com.chouchongkeji.service.friend;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

/**
 * @author yichenshanren
 * @date 2018/6/21
 */

public interface FriendService {

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
    Response addFriend(Integer userId, Integer targetUserId, String validationMsg, String remark, Integer groupId);


    /**
     * 获取好友验证消息
     *
     * @param userDetails 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response getNotifyMsgs(Integer userDetails, PageQuery page);
}
