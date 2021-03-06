package com.chouchongkeji.mvc.controller.user.info;

import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.service.v4.RebateCouponService;
import com.yichen.auth.redis.MRedisTemplate;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.user.info.UserLoginService;
import com.yichen.auth.mvc.AppClient;
import com.yichen.auth.service.UserDetails;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 用户无需登录认证的接口
 *
 * @author linqin
 * @date 2018/6/5
 */
@RestController
@RequestMapping("noauth/user")
public class UserLoginController {
    @Autowired
    private MRedisTemplate mRedisTemplate;

    @Autowired
    private UserLoginService userLoginService;

    @Resource
    private RebateCouponService rebateCouponService;

    /**
     * @Description: 获取上级用户信息
     * @Author: LxH
     * @Date: 2020/11/16 11:43
     */
    @PostMapping("getSuperUserInfo")
    public Response getSuperUserInfo(Integer superId,Integer storeId){
        return rebateCouponService.getSuperUserInfo(superId,storeId);
    }

    /**
     * @Description: 微信小程序一键登录
     * @Author: LxH
     * @Date: 2020/10/20 13:07
     */
    @PostMapping("wxAppletLogin")
    public Response wxAppletLogin(@AppClient Integer client,HttpServletRequest request){
        return userLoginService.wxAppletLogin(client,request);
    }

    /**
     * 微信授权登录
     *
     * @param code   微信授权
     * @param client 1 android 2 ios 3 小程序
     * @return
     * @author linqin
     * @date 2018/6/5
     */
    @PostMapping("/wxLogin")
    public Response wxLogin(@AppClient Integer client, String code) {
        //校验必传参数
        if (StringUtils.isBlank(code)) {
            return ResponseFactory.errMissingParameter();
        }

        return userLoginService.wxLogin(client, code );
    }

    /**
     * 绑定手机号
     *
     * @param phone 电话号码
     * @param key   缓存openId的key
     * @param userId   邀请者用户id
     * @return
     * @author linqin
     * @date 2018/6/5
     */
    @PostMapping("/bindPhone")
    public Response bindPhone(@AppClient Integer client, String phone, String key,Integer userId,AppUser user) {
        //校验必传参数
        if (StringUtils.isAnyBlank(phone, key)) {
            return ResponseFactory.errMissingParameter();
        }
        //根据key取出openId
        String openid = mRedisTemplate.getString(key);
        if (StringUtils.isBlank(openid)) {
            return ResponseFactory.err("key无效或过期!");
        }
        //绑定手机号
        return userLoginService.bindPhone(phone, openid, client,userId,user);

    }


}
