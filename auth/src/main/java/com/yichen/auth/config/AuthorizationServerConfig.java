package com.yichen.auth.config;

import com.yichen.auth.properties.OAuth2ClientProperties;
import com.yichen.auth.properties.SecurityProperties;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 认证服务器配置
 *
 * @author yichenshanren
 * @date 2017/11/25
 */
@SpringBootConfiguration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /* 认证管理器 */
    @Autowired
    private AuthenticationManager authenticationManager;

    /* 安全相关配置 */
    @Autowired
    private SecurityProperties securityProperties;

    /* RedisTokenStore */
    @Autowired
    private TokenStore redisTokenStore;

    @Autowired
    private UnnamedAuthConfig unnamedAuthConfig;

    /**
     * endpoints配置
     *
     * @param endpoints endpoints
     * @throws Exception
     * @author yichenshanren
     * @date 2017/11/25
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(redisTokenStore) // 配置redis TokenStore
                .authenticationManager(authenticationManager)
                .userDetailsService(unnamedAuthConfig.holder().userDetailsService());
    }

    /**
     * 配置client 信息
     *
     * @param clients
     * @throws Exception
     * @author yichenshanren
     * @date 2017/11/25
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory(); // 存在内存中
        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClient())) {
            for (OAuth2ClientProperties client : securityProperties.getOauth2().getClient()) {
                builder.withClient(client.getClientId()) // client Id
                        .secret(client.getClientSecret()) // client secret
                        .accessTokenValiditySeconds(client.getAccessTokenValidityTime()) // 过期时间
                        .refreshTokenValiditySeconds(client.getRefreshTokenValidityTime())
                        .authorizedGrantTypes("password", "refresh_token") // 授权类型
                        .scopes("all"); // 权限
            }
        }
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        return messageSource;
    }


}
