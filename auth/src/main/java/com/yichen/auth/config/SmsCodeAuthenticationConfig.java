package com.yichen.auth.config;

import com.yichen.auth.authentication.sms.SmsCodeAuthenticationFilter;
import com.yichen.auth.authentication.sms.SmsCodeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 短信验证码认证配置
 *
 * @author yichenshanren
 * @date 2017/11/26
 */
@Component
public class SmsCodeAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    // 认证成功处理
    @Autowired
    private AuthenticationSuccessHandler appAuthenticationSuccessHandler;

    // 认证失败处理
    @Autowired
    private AuthenticationFailureHandler appAuthenticationFailedHandler;

    @Autowired
    private UnnamedAuthConfig unnamedAuthConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        // 配置AuthenticationManager
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        // 设置AuthenticationSuccessHandler
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(appAuthenticationSuccessHandler);
        // 设置AuthenticationFailureHandler
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(appAuthenticationFailedHandler);
        // 设置SmsCodeAuthenticationProvider
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(unnamedAuthConfig.holder().userDetailsService());

        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
