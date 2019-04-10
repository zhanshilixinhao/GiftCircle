package com.chouchongkeji.service.user.info;

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


    /**
     * 获取用户的详细信息
     *
     * @param userId       用户信息
     * @param targetUserId 查看的用户id
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    Response getInfo(Integer userId, Integer targetUserId);

    /**
     * 扫描用户的二位啊
     *
     * @param userId 用户id
     * @param qrcode 二维码内容
     * @return
     */
    Response scanQrcode(Integer userId, String qrcode);

    /**
     * 找回密码
     *
     * @param userId 用户信息
     * @param phone       电话号码
     * @param code        短信验证码
     * @param de          加密后的密码
     * @param time         随机字符串
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    Response findSendPwd(Integer userId, Integer client, String phone, String code, String de, String time);

    /**
     * 修改电话号码
     *
     * @param userId
     * @return
     * @author linqin
     * @date 2018/6/5
     */
    Response modifyPhone(Integer userId,String phone);
    /**
     * 用户隐私设置
     *
     * @param userDetails
     * @param isHide      隐私设置 1 不隐藏，2 隐藏
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    Response setHide(Integer userId, Byte isHide);
}
