package com.chouchongkeji.mvc.controller.user.info;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.dial.redis.MRedisTemplate;
import com.chouchongkeji.service.user.info.UserLoginService;
import com.yichen.auth.mvc.AppClient;
import com.yichen.auth.service.ThirdAccService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private ThirdAccService thirdAccService;

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

        return userLoginService.wxLogin(client, code);
    }

    /**
     * 绑定手机号
     *
     * @param phone 电话号码
     * @param key   缓存openId的key
     * @return
     * @author linqin
     * @date 2018/6/5
     */
    @PostMapping("/bindPhone")
    public Response bindPhone(@AppClient Integer client, String phone, String key) {
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
        return thirdAccService.addOpenAccDetail(openid, client == 3 ? 2 : 1, phone);

    }


}
