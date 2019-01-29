package com.chouchongkeji.service.user.friend;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.user.friend.vo.FriendVo;

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
    Response optNotifyMsg(Integer userId, Integer opt, Integer msgId, String reply);

    /**
     * 添加分组
     *
     * @param userId 用户i西南西
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response addGroup(Integer userId, String groupName);

    /**
     * 删除分组
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response delGroup(Integer userId, Integer groupId);

    /**
     * 修改分组名称
     *
     * @param userId    用户信息
     * @param groupName
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response modifyGroup(Integer userId, Integer groupId, String groupName);

    /**
     * 修改好友分组
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response moveGroup(Integer userId, Integer friendUserId, Integer groupId);

    /**
     * 获取用户的分组列表
     *
     * @param userId 用户id
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response getGroupList(Integer userId);

    /**
     * 获取好友列表
     *
     * @param userId  用户id
     * @param groupId
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response getFriendList(Integer userId, Integer groupId);

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
    Response modifyFriend(Integer userId, Integer friendUserId, Integer groupId, String remark, String relationship);

    /**
     * 删除好友
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response delFriend(Integer userId, Integer friendUserId);

    /**
     * 搜索好友
     *
     * @param userId 用户信息
     * @param key 关键字
     * @param  type 1 搜索好友， 2 搜索陌生人， 不传 ：搜索所有
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response searchFriend(Integer userId, String key, Integer type);

    FriendVo isFriend(Integer userId, Integer friendUserId);

    void addWXFriend(Integer userId, Integer friendUserId);

    /**
     *新的朋友消息未查看数量
     * @param userDetails
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    Response getNoReadCount(Integer userId);
}
