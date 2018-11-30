package com.chouchongkeji.service.message.impl;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.dial.dao.backpack.gift.AppMessageMapper;
import com.chouchongkeji.dial.dao.backpack.gift.AppMessageUserMapper;
import com.chouchongkeji.dial.pojo.gift.virtualItem.AppMessage;
import com.chouchongkeji.dial.pojo.gift.virtualItem.AppMessageUser;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.message.MessageService;
import com.chouchongkeji.service.message.vo.MessageHomeVo;
import com.chouchongkeji.util.Constants;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/7/3
 */

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private AppMessageMapper appMessageMapper;

    @Autowired
    private AppMessageUserMapper appMessageUserMapper;

    /**
     * 添加一条消息
     *
     * @param message 消息内容
     * @param userIds 用户id集合
     * @return
     * @author yichenshanren
     * @date 2018/7/3
     */
    @Override
    public int addMessage(AppMessage message, List<Integer> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            throw new ServiceException(ErrorCode.ERROR);
        }
        message.setId(null);
        // 添加消息内容
        int count = appMessageMapper.insert(message);
        if (count < 1) {
            throw new ServiceException(ErrorCode.ERROR);
        }
        // 添加消息关联
        List<AppMessageUser> users = new ArrayList<>();
        for (Integer userId : userIds) {
            users.add(assembleAppMessageUser(userId, message));
        }
        // 批量插入
        count = appMessageUserMapper.insertBatch(users);
        if (count < 1) {
            throw new ServiceException(ErrorCode.ERROR);
        }
        return count;
    }

    /**
     * 添加一条消息
     *
     * @param type     消息类型
     * @param summary  消息简介
     * @param content  消息内容
     * @param targetId 目标id
     * @param userIds  用户id集合
     * @return
     * @author yichenshanren
     * @date 2018/7/3
     */
    @Override
    public int addMessage(Constants.APP_MESSAGE_TYPE type, String summary, Object content, Integer targetId, List<Integer> userIds) {
        AppMessage message = new AppMessage();
        message.setTitle(type.title());
        message.setMessageType(type.type());
        message.setSummary(summary);
        message.setContent(JSON.toJSONString(content));
        message.setTargetId(Long.valueOf(targetId));
        return addMessage(message, userIds);
    }

    /**
     * 添加一条消息
     *
     * @param type     消息类型
     * @param summary  消息简介
     * @param content  消息内容
     * @param targetId 目标id
     * @param userId   用户id集合
     * @return
     * @author yichenshanren
     * @date 2018/7/3
     */
    @Override
    public int addMessage(Constants.APP_MESSAGE_TYPE type, String summary, Object content, Integer targetId, Integer userId) {
        List<Integer> userIds = new ArrayList<>();
        userIds.add(userId);
        return addMessage(type, summary, content, targetId, userIds);
    }

    /**
     * 组床
     *
     * @param userId  用户id
     * @param message 消息内容
     * @return
     * @author yichenshanren
     * @date 2018/7/3
     */
    private AppMessageUser assembleAppMessageUser(Integer userId, AppMessage message) {
        AppMessageUser user = new AppMessageUser();
        user.setAppMessageId(message.getId());
        user.setIsRead(Constants.MESSAGE_READ.NO);
        user.setUserId(userId);
        return user;
    }


    /**
     * 消息主页 列表
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/7/6
     */
    @Override
    public Response getHomeList(Integer userId) {
        List<MessageHomeVo> vos = new ArrayList<>();
        for (Constants.APP_MESSAGE_TYPE type : Constants.APP_MESSAGE_TYPE.values()) {
            // 查询最后一条消息
            MessageHomeVo vo = appMessageUserMapper.selectLastMessageByUserIdAndType(userId, type.type());
            if (vo == null) {
                vo = new MessageHomeVo();
                vo.setCreated(new Date());
            }
            // 根据类型查询
            Integer unread = appMessageUserMapper.selectUnredByUserIdAndType(userId, type.type());
            vo.setUnread(unread);
            vo.setTitle(type.title());
            vo.setMessageType(type.type());
            vos.add(vo);
        }
        return ResponseFactory.sucData(vos);
    }


    /**
     * 获取消息列表
     *
     * @param userId      用户id
     * @param messageType 消息类型 1 礼物 2 系统 3 寄售台 4 礼品交换
     * @param page        分页
     * @return
     * @author yichenshanren
     * @date 2018/7/6
     */
    @Override
    public Response getMessageList(Integer userId, Byte messageType, PageQuery page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List vos = null;
        switch (messageType) {
            case 1:
                vos = appMessageUserMapper.selectListByUserIdAndType(userId);
                break;
            case 2:
                vos = appMessageUserMapper.selectSystemByUserIdAndType(userId);
                break;
            case 3:
                vos = appMessageUserMapper.selectConMessageByUserId(userId);
                break;
//            case 4:
//                vos = appMessageUserMapper.selectConMessageByUserId(userId);
//                break;
        }
        return ResponseFactory.sucData(vos);
    }
}
