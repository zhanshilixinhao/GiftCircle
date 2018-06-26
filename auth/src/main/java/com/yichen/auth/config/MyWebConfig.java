package com.yichen.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yichen.auth.jackson.MyBeanSerializerModifier;
import com.yichen.auth.mvc.AppClientMethodArgumentResolver;
import com.yichen.auth.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/6/5
 */

public class MyWebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AppClientMethodArgumentResolver appClientMethodArgumentResolver;

    /**
     * 自定义的数据绑定
     * 取出请求的客户端类型 1 Android 2 iOS 3 小程序
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(appClientMethodArgumentResolver);
    }

    /**
     * jackson null值处理
     *
     * @return
     * @author yichenshanren
     * @date 2017/11/23
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = converter.getObjectMapper();
        // 为mapper注册一个带有SerializerModifier的Factory，此modifier主要做的事情为：当序列化类型为array，list、set时，当值为空时，序列化成[]
        MyBeanSerializerModifier myBeanSerializerModifier = new MyBeanSerializerModifier();
        myBeanSerializerModifier.setImageProperties(securityProperties.getImage());
        mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(myBeanSerializerModifier));
        return converter;
    }
}
