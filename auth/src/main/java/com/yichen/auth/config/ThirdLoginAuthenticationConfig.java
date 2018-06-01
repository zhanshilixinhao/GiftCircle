package com.yichen.auth.config;

import com.yichen.auth.authentication.openid.ThirdLoginAuthenticationFilter;
import com.yichen.auth.authentication.openid.ThirdLoginAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 第三方账号登录 认证配置
 *
 * @author yichenshanren
 * @date 2017/11/30
 */

@Configuration
public class ThirdLoginAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

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
        // 创建第三方登录认证过滤器
        ThirdLoginAuthenticationFilter thirdLoginAuthenticationFilter =
                new ThirdLoginAuthenticationFilter();
        // 配置AuthenticationManager
        thirdLoginAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        // 配置AuthenticationSuccessHandler
        thirdLoginAuthenticationFilter.setAuthenticationSuccessHandler(appAuthenticationSuccessHandler);
        // 配置AuthenticationFailHandler
        thirdLoginAuthenticationFilter.setAuthenticationFailureHandler(appAuthenticationFailedHandler);
        // 创建AuthenticationProvider
        ThirdLoginAuthenticationProvider provider = new ThirdLoginAuthenticationProvider();
        // 配置用户信息服务
        provider.setUserDetailsService(unnamedAuthConfig.holder().userDetailsService());
        // 配置第三方账号信息服务
        provider.setThirdAccService(unnamedAuthConfig.holder().thirdAccService());

        http.authenticationProvider(provider)
                .addFilterAfter(thirdLoginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
