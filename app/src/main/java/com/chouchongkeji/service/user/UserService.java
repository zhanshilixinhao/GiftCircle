package com.chouchongkeji.service.user;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.pojo.user.AppUser;

/**
 * @author linqin
 * @date 2018/6/5
 */
public interface UserService {
    /**
     * 获取用户详细信息
     *
     * @author linqin
     * @date 2018/6/5
     */
    Response getProfile(Integer memberId);


    /**
     * 修改用户信息
     *
     * @param appUser
     * @return
     * @author linqin
     * @date 2018/6/5
     */
    Response updateProfile(Integer userId, AppUser appUser);

    /**
     * 赠送密码之前 请求获取赠送密码状态
     *
     * @param userId 用户信息
     * @param s1     随机数
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    Response preSentPwd(Integer userId, String s1);

    /**
     * 赠送密码之前 请求获取赠送密码状态
     *
     * @param userId 用户信息
     * @param de     加密后的密码
     * @param time   随机字符串
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    Response setSentPwd(Integer userId, String de, String time);
}
