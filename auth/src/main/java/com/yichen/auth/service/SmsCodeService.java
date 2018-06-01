package com.yichen.auth.service;

import com.chouchongkeji.goexplore.common.Response;

/**
 * 短信验证码发送
 * 验证
 *
 * @author yichenshanren
 * @date 2017/11/26
 */

public interface SmsCodeService {

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     * @param type  短信验证码类型
     * @return 发送结果
     */
    Response sendSmsCode(String phone, Integer type);

    /**
     * 验证短信验证码
     *
     * @param phone 手机号
     * @param type  验证码类型
     * @param code  验证码
     * @return 验证结果
     */
    Response verifySmsCode(String phone, Integer type, String code);


}
