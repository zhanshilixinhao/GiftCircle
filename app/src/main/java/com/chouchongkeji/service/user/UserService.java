package com.chouchongkeji.service.user;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.dial.pojo.user.AppUser;

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
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    Response preSentPwd(Integer userId);

    /**
     * 赠送密码之前 请求获取赠送密码状态
     *
     * @param userId 用户信息
     * @param de     加密后的密码
     * @param time   随机字符串
     * @param client
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    Response setSentPwd(Integer userId, String de, String time, Integer client);

    /**
     * 修改密码之前验证原密码是否正确
     *
     * @param userId 用户信息
     * @param de     加密后的密码
     * @param time   随机字符串
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    Response changePwdVerify(Integer userId, String de, String time, Integer client);

    /**
     * 修改赠送密码
     *
     * @param userId 用户信息
     * @param de     加密后的密码
     * @param time   随机字符串
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    Response changePwd(Integer userId, String de, String time, Integer client, String key);
}
