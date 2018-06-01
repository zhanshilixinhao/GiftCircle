package com.yichen.auth.config;

import com.yichen.auth.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yichenshanren
 * @date 2017/11/26
 */

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class PropertiesConfig {
}
