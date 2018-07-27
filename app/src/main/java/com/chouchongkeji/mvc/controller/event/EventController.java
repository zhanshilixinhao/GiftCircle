package com.chouchongkeji.mvc.controller.event;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.event.EventService;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2018/6/12
 */
@RestController
@RequestMapping("auth/event")
public class EventController {

    @Autowired
    private EventService eventService;

    /**
     * 事件列表
     *
     * @return
     * @author linqin
     * @date 2018/6/12
     */
    @PostMapping("list")
    public Response eventList(@AuthenticationPrincipal UserDetails userDetails) {
        return eventService.getAllList(userDetails.getUserId());
    }


    /**
     * 添加事件
     *
     * @param userDetails
     * @param eventName   事件名称
     * @return
     * @author linqin
     * @date 2018/6/12
     */
    @PostMapping("add")
    public Response addEvent(@AuthenticationPrincipal UserDetails userDetails, String eventName) {
        if (StringUtils.isBlank(eventName)) {
            return ResponseFactory.errMissingParameter();
        }
        return eventService.addEvent(userDetails.getUserId(), eventName);
    }


}
