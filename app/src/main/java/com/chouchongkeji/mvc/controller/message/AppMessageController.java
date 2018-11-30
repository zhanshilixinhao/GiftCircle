package com.chouchongkeji.mvc.controller.message;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.message.MessageService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yichenshanren
 * @date 2018/7/6
 */

@RestController
@RequestMapping("auth/v1/message")
public class AppMessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 消息主页 列表
     *
     * @param userDetails 用户xinx
     * @return
     * @author yichenshanren
     * @date 2018/7/6
     */
    @PostMapping("home")
    public Response getUnreadMessage(@AuthenticationPrincipal UserDetails userDetails) {
        return messageService.getHomeList(userDetails.getUserId());
    }


    /**
     * 消息主页 列表
     *
     * @param userDetails 用户xinx
     * @return
     * @author yichenshanren
     * @date 2018/7/6
     */
    @PostMapping("list")
    public Response getMessageList(@AuthenticationPrincipal UserDetails userDetails,
                                   Byte messageType, PageQuery page) {
        if (messageType == null || messageType < 1 || messageType > 4) {
            return ResponseFactory.errMissingParameter();
        }
        return messageService.getMessageList(userDetails.getUserId(), messageType, page);
    }




}
