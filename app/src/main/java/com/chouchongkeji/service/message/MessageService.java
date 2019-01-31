package com.chouchongkeji.service.message;

import com.chouchongkeji.dial.pojo.gift.virtualItem.AppMessage;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.util.Constants;

import java.util.HashSet;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/7/3
 */

public interface MessageService {

    /**
     * 添加一条消息
     *
     * @param message 消息内容
     * @param userIds 用户id集合
     * @return
     * @author yichenshanren
     * @date 2018/7/3
     */
    int addMessage(AppMessage message, List<Integer> userIds);


    /**
     * 添加一条消息
     *
     * @param summary 消息内容
     * @param userIds 用户id集合
     * @return
     * @author yichenshanren
     * @date 2018/7/3
     */
    int addMessage(Constants.APP_MESSAGE_TYPE type, String summary, Object content, Integer targetId, List<Integer> userIds);

    /**
     * 添加一条消息
     *
     * @param summary 消息内容
     * @param userId  用户id
     * @return
     * @author yichenshanren
     * @date 2018/7/3
     */
    int addMessage(Constants.APP_MESSAGE_TYPE type, String summary, Object content, Integer targetId, Integer userId);

    /**
     * 消息主页 列表
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/7/6
     */
    Response getHomeList(Integer userId);

    /**
     * 获取消息列表
     *
     * @param userId      用户id
     * @param messageType 消息类型
     * @param page        分页
     * @return
     * @author yichenshanren
     * @date 2018/7/6
     */
    Response getMessageList(Integer userId, Byte messageType, PageQuery page);

    /**
     * 将消息标记为已读
     *
     * @param userId    用户id
     * @param messageId 消息
     * @return
     */
    Response readMessage(Integer userId, HashSet<Integer> messageId);
}
