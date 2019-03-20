package com.chouchongkeji.mvc.controller.user.friend;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.user.friend.LeaveMessageService;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2019/3/20 14:40
 */
@RestController
@RequestMapping("auth/leaveMessage")
public class LeaveMessageController {

    @Autowired
    private LeaveMessageService leaveMessageService;

    /**
     * 给好友留言
     *
     * @param userDetails
     * @param friendUserId 好友id
     * @param detail       留言内容
     * @return
     * @author linqin
     * @date 2019/3/20
     */
    @PostMapping("add")
    public Response leaveMessage(@AuthenticationPrincipal UserDetails userDetails, Integer friendUserId, String detail) {
        if (friendUserId == null || StringUtils.isBlank(detail)){
            return ResponseFactory.errMissingParameter();
        }
        return leaveMessageService.leaveMessage(userDetails.getUserId(),friendUserId,detail);
    }

}
