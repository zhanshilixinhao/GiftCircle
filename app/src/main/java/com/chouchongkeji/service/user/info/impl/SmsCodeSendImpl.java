package com.chouchongkeji.service.user.info.impl;

import com.chouchongkeji.util.SendUtil;
import com.yichen.auth.verify.SmsCodeSender;
import com.yichen.auth.verify.SmsSendResult;
import org.springframework.stereotype.Service;

/**
 * @author linqin
 * @date 2018/5/18
 */
@Service
public class SmsCodeSendImpl implements SmsCodeSender {
    @Override
    public SmsSendResult send(String phone, String code) {
        return null;
    }

    @Override
    public SmsSendResult sendTemplate(String phone, String code, int tid) {
        return SendUtil.smsSend(phone,code);
//     return new SmsSendResult(0);
    }
}
