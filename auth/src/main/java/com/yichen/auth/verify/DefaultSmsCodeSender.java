package com.yichen.auth.verify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yichenshanren
 * @date 2017/11/26
 */

public class DefaultSmsCodeSender implements SmsCodeSender {

    private Logger logger = LoggerFactory.getLogger(DefaultSmsCodeSender.class);

    /**
     * @param phone
     * @param code
     */
    @Override
    public SmsSendResult send(String phone, String code) {
        logger.info(String.format("发送验证码%s到%s", code, phone));
        return new SmsSendResult(0);
    }

    /**
     * @param phone
     * @param code
     * @param tid
     */
    @Override
    public SmsSendResult sendTemplate(String phone, String code, int tid) {
        return new SmsSendResult(0);
    }
}
