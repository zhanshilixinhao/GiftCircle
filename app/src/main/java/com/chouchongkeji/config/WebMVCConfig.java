package com.chouchongkeji.config;

import com.chouchongkeji.mvc.converter.LongDateConverter;
import com.yichen.auth.config.MyWebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

/**
 * spring mvc 配置
 *
 * @author yichenshanren
 * @date 2018/6/5
 */

@SpringBootConfiguration
public class WebMVCConfig extends MyWebConfig {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void addConversionConfig() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer)
                requestMappingHandlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService =
                    (GenericConversionService)initializer.getConversionService();
            genericConversionService.addConverter(new LongDateConverter());
        }
    }
}
