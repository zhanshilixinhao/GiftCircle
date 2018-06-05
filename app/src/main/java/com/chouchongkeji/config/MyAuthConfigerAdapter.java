package com.chouchongkeji.config;

import com.yichen.auth.config.cus.AuthConfigerAdapter;
import com.yichen.auth.config.cus.AuthHolder;
import com.yichen.auth.service.ThirdAccService;
import com.yichen.auth.verify.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author linqin
 * @date 2018/5/18
 */
@SpringBootConfiguration
public class MyAuthConfigerAdapter  extends AuthConfigerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    private ThirdAccService thirdAccService;

    public void config(AuthHolder authHolder){
        authHolder
                .userDetailsService(userDetailsService) // 用户获取用户详情
                .smsCodeSender(smsCodeSender)           //用于发送短信验证
                .thirdAccService(thirdAccService);      //用于处理第三方账号登录
    }

}
