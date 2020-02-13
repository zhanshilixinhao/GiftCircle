package com.chouchongkeji.mvc.controller.user.info;

import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.push.AppPush;
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

    @PostMapping("/scanQrcode")
    public Response scanQrcode(@AuthenticationPrincipal UserDetails userDetails, String qrcode) {
        return userService.scanQrcode(userDetails.getUserId(), qrcode);
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
     * 修改电话号码
     *
     * @param userDetails
     * @return
     * @author linqin
     * @date 2018/6/5
     */
    @PostMapping("modify_phone")
    public Response modifyPhone(@AuthenticationPrincipal UserDetails userDetails, String phone) {
        if (StringUtils.isBlank(phone)) {
            return ResponseFactory.errMissingParameter();
        }
        return userService.modifyPhone(userDetails.getUserId(), phone);
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
     * @param s2          随机字符串
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @PostMapping("set/pwd")
    public Response setSentPwd(@AuthenticationPrincipal UserDetails userDetails,
                               @AppClient Integer client, String de, String s2) {
        String time = s2;
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
                                    @AppClient Integer client, String de, String s2) {
        String time = s2;
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
     * @param s2          随机字符串
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @PostMapping("change/pwd")
    public Response changePwd(@AuthenticationPrincipal UserDetails userDetails,
                              @AppClient Integer client, String de, String s2, String key) {
        String time = s2;
        if (StringUtils.isAnyBlank(de, time)) {
            return ResponseFactory.errMissingParameter();
        }
        return userService.changePwd(userDetails.getUserId(), de, time, client, key);
    }

    /**
     * 找回密码
     *
     * @param userDetails 用户信息
     * @param phone       电话号码
     * @param code        短信验证码
     * @param de          加密后的密码
     * @param s2          随机字符串
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @PostMapping("find/pwd")
    public Response findSendPwd(@AuthenticationPrincipal UserDetails userDetails, @AppClient Integer client, String phone,
                                String code, String de, String s2) {
        String time = s2;
        if (StringUtils.isAnyBlank(phone, code, de, s2, time)) {
            return ResponseFactory.errMissingParameter();
        }
        return userService.findSendPwd(userDetails.getUserId(), client, phone, code, de, time);
    }


    /**
     * 获取用户的详细信息
     *
     * @param userDetails  用户信息
     * @param targetUserId 查看的用户id
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @PostMapping("info")
    public Response getInfo(@AuthenticationPrincipal UserDetails userDetails,
                            Integer targetUserId) {
        if (targetUserId == null) {
            targetUserId = userDetails.getUserId();
        }
        return userService.getInfo(userDetails.getUserId(), targetUserId);
    }


    @PostMapping("band")
    public Response bandClientid(@AuthenticationPrincipal UserDetails userDetails, String clientid) throws Exception {
        if (StringUtils.isBlank(clientid)) {
            return ResponseFactory.errMissingParameter();
        }
        AppPush.band(userDetails.getUserId(), clientid);
        return ResponseFactory.suc();
    }


    /**
     * 用户隐私设置
     *
     * @param userDetails
     * @param isHide      隐私设置 1 不隐藏，2 隐藏
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @PostMapping("set_hide")
    public Response setHide(@AuthenticationPrincipal UserDetails userDetails, Byte isHide) {
        if (isHide == null) {
            isHide = 1;
        }
        return userService.setHide(userDetails.getUserId(), isHide);
    }


    /**
     * 融云聊天
     *
     * @param userId   用户id
     * @param nickname 用户昵称
     * @param avatar   用户头像
     * @return
     * @author linqin
     * @date 2019/6/21
     */
    @PostMapping("ryUser")
    public Response ryUserRegister(@AuthenticationPrincipal UserDetails userDetails, String nickname, String avatar) throws Exception {
        if (StringUtils.isAnyBlank(nickname, avatar) ) {
            return ResponseFactory.errMissingParameter();
        }
        return ResponseFactory.sucData(userService.ryUserRegister(userDetails.getUserId(),nickname,avatar));
    }
}
