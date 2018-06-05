package com.chouchongkeji.controller.user;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.pojo.user.AppUser;
import com.chouchongkeji.service.user.UserService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2018/6/5
 */
@RestController
@RequestMapping("auth/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户详细信息
     *
     * @param userDetails
     * @return
     * @author linqin
     * @date 2018/6/5
     */
    @PostMapping("/profile")
    public Response profile(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getProfile(userDetails.getUserId());
    }


    /**
     * 修改用户信息
     *
     * @param userDetails
     * @param appUser
     * @return
     * @author linqin
     * @date 2018/6/5
     */
    @PostMapping("/modify_profile")
    public Response modifyProfile(@AuthenticationPrincipal UserDetails userDetails, AppUser appUser) {
        return userService.updateProfile(userDetails.getUserId(), appUser);

    }


}
