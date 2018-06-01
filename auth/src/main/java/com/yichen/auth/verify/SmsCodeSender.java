package com.yichen.auth.verify;

/**
 * 短信验证码发送
 *
 * @author yichenshanren
 * @date 2017/11/26
 */

public interface SmsCodeSender {

    /**
     * 发送验证码
     *
     * @param phone
     * @param code
     */
     SmsSendResult send(String phone, String code);

    /**
     * 按模板发送验证码
     *
     * @param phone
     * @param code
     * @param tid
     */
    SmsSendResult sendTemplate(String phone, String code, int tid);

}
