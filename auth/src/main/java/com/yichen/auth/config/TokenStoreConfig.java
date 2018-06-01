package com.yichen.auth.config;

import com.yichen.auth.service.UserDtailsCacheService;
import com.yichen.auth.service.impl.DefaultUserDetailCahcerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * token 存储配置
 *
 * @author yichenshanren
 * @date 2017/12/1
 */
@Configuration
public class TokenStoreConfig {

    /* redis 连接工产 */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 配置redis 存储token
     *
     * @return
     * @author yichenshanren
     * @date 2017/12/1
     */
    @Bean
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }


    @Bean
    @ConditionalOnMissingBean(UserDtailsCacheService.class)
    public UserDtailsCacheService userDtailsCacheService() {
        return new DefaultUserDetailCahcerService();
    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
