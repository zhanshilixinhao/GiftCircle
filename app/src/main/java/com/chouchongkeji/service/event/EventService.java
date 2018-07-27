package com.chouchongkeji.service.event;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author linqin
 * @date 2018/6/12
 */
public interface EventService {

    /**
     * 事件列表
     * @return
     * @author linqin
     * @date 2018/6/12
     */
    Response getAllList(Integer userId);

    /**
     * 添加事件
     *
     * @param userId
     * @param eventName   事件名称
     * @return
     * @author linqin
     * @date 2018/6/12
     */
    Response addEvent(Integer userId, String eventName);
}
