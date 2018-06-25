package com.chouchongkeji.mvc.controller.event;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2018/6/12
 */
@RestController
@RequestMapping("noauth/event")
public class EventController {

    @Autowired
    private EventService eventService;

    /**
     * 事件列表
     * @return
     * @author linqin
     * @date 2018/6/12
     */
    @PostMapping("/list")
    public Response eventList(){
        return eventService.getList();
    }

}
