package com.yichen.auth.config;

import com.yichen.auth.exception.HeaderOnlyOAuth2ExceptionRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * spring oauth2 异常处理 配置
 *
 * @author yichenshanren
 * @date 2017/11/29
 */
@Configuration
public class OAuth2ExceptionConfig {

    @Autowired
    private HeaderOnlyOAuth2ExceptionRenderer headerOnlyOAuth2ExceptionRenderer;

    @Autowired
    private WebResponseExceptionTranslator myWebResponseExceptionTranslator;

    @Bean()
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
        OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler = new OAuth2AccessDeniedHandler();
        oAuth2AccessDeniedHandler.setExceptionRenderer(headerOnlyOAuth2ExceptionRenderer);
        oAuth2AccessDeniedHandler.setExceptionTranslator(myWebResponseExceptionTranslator);
        return oAuth2AccessDeniedHandler;
    }


    @Bean()
    public OAuth2AuthenticationEntryPoint oauthAuthenticationEntryPoint() {
        OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        oAuth2AuthenticationEntryPoint.setExceptionRenderer(headerOnlyOAuth2ExceptionRenderer);
        oAuth2AuthenticationEntryPoint.setExceptionTranslator(myWebResponseExceptionTranslator);
        return oAuth2AuthenticationEntryPoint;
    }

    @Bean()
    public OAuth2AuthenticationEntryPoint clientAuthenticationEntryPoint() {
        OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        oAuth2AuthenticationEntryPoint.setExceptionRenderer(headerOnlyOAuth2ExceptionRenderer);
        oAuth2AuthenticationEntryPoint.setExceptionTranslator(myWebResponseExceptionTranslator);
        oAuth2AuthenticationEntryPoint.setTypeName("Basic");
        return oAuth2AuthenticationEntryPoint;
    }

}
