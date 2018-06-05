package com.yichen.auth.config;

import com.yichen.auth.SecurityConstants;
import com.yichen.auth.verify.FakeTokenFilter;
import com.yichen.auth.verify.SignFilter;
import com.yichen.auth.verify.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * oauth2资源服务器配置
 *
 * @author yichenshanren
 * @date 2017/11/25
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /* 认证成功成功处理 */
    @Autowired
    private AuthenticationSuccessHandler appAuthenticationSuccessHandler;

    /* 认证失败处理 */
    @Autowired
    private AuthenticationFailureHandler appAuthenticationFailedHandler;

    /* 短信验证码验证过滤器 */
    @Autowired
    private SmsCodeFilter smsCodeFilter;

    @Autowired
    private SignFilter signFilter;

    @Autowired
    protected OAuth2AuthenticationEntryPoint oauthAuthenticationEntryPoint;

    @Autowired
    private OAuth2AccessDeniedHandler oauthAccessDeniedHandler;

    /* 手机短信验证码登录认证配置 */
    @Autowired
    private SmsCodeAuthenticationConfig smsCodeAuthenticationConfig;

    /* 第三方账号登录认证配置 */
    @Autowired
    private ThirdLoginAuthenticationConfig thirdLoginAuthenticationConfig;

    @Autowired
    private TokenStore redisTokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(oauthAuthenticationEntryPoint)
                .accessDeniedHandler(oauthAccessDeniedHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class) // 在UsernamePasswordAuthenticationFilter之前
                .addFilterBefore(signFilter, SmsCodeFilter.class)
//                .addFilterBefore(resourcesServerFilter, AbstractPreAuthenticatedProcessingFilter.class)
                .formLogin() // 使用表单方式d登录
                .loginPage(SecurityConstants.PHONE_LOGIN_PAGE_URL)  // 配置默认的登录跳转url
                .loginProcessingUrl(SecurityConstants.PHONE_LOGIN_URL)  // 配置默认的用户名密码登录请求url
                .successHandler(appAuthenticationSuccessHandler) // 登陆成功处理器
                .failureHandler(appAuthenticationFailedHandler) // 登录失败处理器
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.PHONE_LOGIN_PAGE_URL,
                        SecurityConstants.PHONE_LOGIN_URL,
                        SecurityConstants.ASK_CODE,
                        SecurityConstants.NOAUTH_URL,
                        SecurityConstants.FAKE_URL,
                        SecurityConstants.DRUID,
                        "/token/refresh"
                ).permitAll() // 不需要认证的请求
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .apply(smsCodeAuthenticationConfig)
                .and()
                .apply(thirdLoginAuthenticationConfig);
    }

    /**
     * 配置假冒tooken过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean fakeFilterRegistrationBean() {
        // 处理access_token认证管理器
        OAuth2AuthenticationManager auth2AuthenticationManager = new OAuth2AuthenticationManager();
        // token处理
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        // token存储器
        defaultTokenServices.setTokenStore(redisTokenStore);
        auth2AuthenticationManager.setTokenServices(defaultTokenServices);
        // 假冒的token过滤器
        FakeTokenFilter fakeTokenFilter = new FakeTokenFilter();
        fakeTokenFilter.setAuthenticationManager(auth2AuthenticationManager);

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(fakeTokenFilter);
        registration.setName("fakeTokenFilter");
        return registration;
    }



}
