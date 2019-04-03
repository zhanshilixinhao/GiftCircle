package com.chouchongkeji.mvc.controller.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.iwant.wallet.FireworksService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @description 礼花、邀请好友
 * @date 2019/4/3
 */
@RestController
@RequestMapping("auth/v1/fireworks/")
public class FireworksController {

    @Autowired
    private FireworksService fireworksService;


    /**
     * 用户礼花详情
     * @param details
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    @PostMapping("detail")
    public Response fireworksDetail(@AuthenticationPrincipal UserDetails details) {
        return fireworksService.fireworksDetail(details.getUserId());
    }


    /**
     * 我的团队
     * @param userDetails
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    @PostMapping("user_list")
    public Response getInviteUserList(@AuthenticationPrincipal UserDetails userDetails){
        return fireworksService.getInviteUserList(userDetails.getUserId());
    }



}
