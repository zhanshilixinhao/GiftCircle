package com.chouchongkeji.mvc.controller.user.info;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.service.user.info.UserService;
import com.yichen.auth.mvc.AppClient;
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
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @PostMapping("pre/pwd")
    public Response preSentPwd(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.preSentPwd(userDetails.getUserId());
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
    public Response setSentPwd(@AuthenticationPrincipal UserDetails userDetails,
                               @AppClient Integer client, String de, String time) {
        if (StringUtils.isAnyBlank(de, time)) {
            return ResponseFactory.errMissingParameter();
        }
        return userService.setSentPwd(userDetails.getUserId(), de, time, client);
    }

    /**
     * 修改密码之前验证原密码是否正确
     *
     * @param userDetails 用户信息
     * @param de          加密后的密码
     * @param time        随机字符串
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @PostMapping("change/pwd/verify")
    public Response changePwdVerify(@AuthenticationPrincipal UserDetails userDetails,
                                    @AppClient Integer client, String de, String time) {
        if (StringUtils.isAnyBlank(de, time)) {
            return ResponseFactory.errMissingParameter();
        }
        return userService.changePwdVerify(userDetails.getUserId(), de, time, client);
    }

    /**
     * 修改赠送密码
     *
     * @param userDetails 用户信息
     * @param de          加密后的密码
     * @param time        随机字符串
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @PostMapping("change/pwd")
    public Response changePwd(@AuthenticationPrincipal UserDetails userDetails,
                              @AppClient Integer client, String de, String time, String key) {
        if (StringUtils.isAnyBlank(de, time)) {
            return ResponseFactory.errMissingParameter();
        }
        return userService.changePwd(userDetails.getUserId(), de, time, client, key);
    }


}
