package com.chouchongkeji.service.event.impl;

import com.chouchongkeji.dial.dao.event.EventMapper;
import com.chouchongkeji.dial.pojo.event.Event;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.event.EventService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author linqin
 * @date 2018/6/12
 */
@Service
@Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)
public class EventServiceImpl implements EventService {

    @Autowired
    private EventMapper eventMapper;

    /**
     * 事件列表
     * @return
     * @author linqin
     * @date 2018/6/12
     */
    @Override
    public Response getAllList(Integer userId) {
       List<Event>  event = eventMapper.selectAll(userId);
       if (event == null){
           return ResponseFactory.err("事件为空，需自己添加");
       }
       return ResponseFactory.sucData(event);
    }


    /**
     * 添加事件
     *
     * @param userId
     * @param eventName   事件名称
     * @return
     * @author linqin
     * @date 2018/6/12
     */
    @Override
    public Response addEvent(Integer userId, String eventName) {
        //查询列表
        List<Event>  event = eventMapper.selectAll(userId);
        if (event == null){
            return ResponseFactory.err("事件为空，需自己添加");
        }
        for (Event event1 : event) {
            if (StringUtils.equals(event1.getEventName(),eventName)){
                throw new ServiceException(ErrorCode.ERROR.getCode(),"该事件已经添加过，请重新添加其他事件");
            }
        }
        Event ev = new Event();
        ev.setUserId(userId);
        ev.setEventName(eventName);
        int insert = eventMapper.insert(ev);
        if (insert<0){
            return ResponseFactory.err("添加事件失败");
        }
        return ResponseFactory.sucMsg("事件添加成功");
    }
}
