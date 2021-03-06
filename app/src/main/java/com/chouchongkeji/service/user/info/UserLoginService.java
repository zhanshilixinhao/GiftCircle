package com.chouchongkeji.service.user.info;

import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.goexplore.common.Response;
import com.yichen.auth.mvc.AppClient;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

/**
 * @author linqin
 * @date 2018/6/5
 */
public interface UserLoginService {
    Response wxLogin( Integer client, String code);

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
    Response bindPhone(String phone, String openid, Integer client, Integer userId, AppUser user);

    /**
     * @Description: 微信小程序一键登录
     * @Author: LxH
     * @Date: 2020/10/20 13:07
     */
    Response wxAppletLogin(Integer client,HttpServletRequest request);
}
