package com.chouchongkeji.controller.user;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.pojo.user.AppUser;
import com.chouchongkeji.service.user.UserService;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 赠送密码之前 请求获取赠送密码状态
     *
     * @param userDetails 用户信息
     * @param s1          随机数
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @PostMapping("pre/pwd")
    public Response preSentPwd(@AuthenticationPrincipal UserDetails userDetails, String s1) {
        if (StringUtils.isBlank(s1) || s1.length() < 16) {
            return ResponseFactory.err("s1长度太小");
        }
        return userService.preSentPwd(userDetails.getUserId(), s1);
    }


    /**
     * 赠送密码之前 请求获取赠送密码状态
     *
     * @param userDetails 用户信息
     * @param de          加密后的密码
     * @param time        随机字符串
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @PostMapping("set/pwd")
    public Response setSentPwd(@AuthenticationPrincipal UserDetails userDetails, String de, String time) {
        if (StringUtils.isAnyBlank(de, time)) {
            return ResponseFactory.errMissingParameter();
        }
        return userService.setSentPwd(userDetails.getUserId(), de, time);
    }

}
