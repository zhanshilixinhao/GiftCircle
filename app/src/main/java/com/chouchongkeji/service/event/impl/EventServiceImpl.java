package com.chouchongkeji.service.event.impl;

import com.chouchongkeji.dial.dao.event.EventMapper;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.dial.pojo.event.Event;
import com.chouchongkeji.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linqin
 * @date 2018/6/12
 */
@Service
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
    public Response getList() {
        List<Event> events = eventMapper.selectAll();
        return ResponseFactory.sucData(events);
    }
}
