package com.yichen.auth.config.cus;

import com.yichen.auth.service.ThirdAccService;
import com.yichen.auth.verify.SmsCodeSender;
import com.yichen.auth.verify.VerifyCodeGenerator;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author yichenshanren
 * @date 2018/5/7
 */

public class AuthHolder {

    private UserDetailsService userDetailsService;

    private SmsCodeSender smsCodeSender;

    private ThirdAccService thirdAccService;

    private VerifyCodeGenerator verifyCodeGenerator;


    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    public AuthHolder userDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        return this;
    }

    public SmsCodeSender smsCodeSender() {
        return smsCodeSender;
    }

    public AuthHolder smsCodeSender(SmsCodeSender smsCodeSender) {
        this.smsCodeSender = smsCodeSender;
        return this;
    }

    public ThirdAccService thirdAccService() {
        return thirdAccService;
    }

    public AuthHolder thirdAccService(ThirdAccService thirdAccService) {
        this.thirdAccService = thirdAccService;
        return this;
    }

    public VerifyCodeGenerator verifyCodeGenerator() {
        return verifyCodeGenerator;
    }

    public AuthHolder verifyCodeGenerator(VerifyCodeGenerator verifyCodeGenerator) {
        this.verifyCodeGenerator = verifyCodeGenerator;
        return this;
    }
}
