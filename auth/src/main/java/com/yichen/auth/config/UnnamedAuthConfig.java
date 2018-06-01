package com.yichen.auth.config;

import com.yichen.auth.config.cus.AuthConfiger;
import com.yichen.auth.config.cus.AuthHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;

import javax.annotation.PostConstruct;

/**
 * @author yichenshanren
 * @date 2018/5/7
 */
@SpringBootConfiguration
public class UnnamedAuthConfig {

    @Autowired
    private AuthConfiger authConfiger;

    private AuthHolder authHolder;

    @PostConstruct
    public void init() {
        authHolder = new AuthHolder();
        authConfiger.config(authHolder);
    }

    public AuthHolder holder() {
        return authHolder;
    }

}
