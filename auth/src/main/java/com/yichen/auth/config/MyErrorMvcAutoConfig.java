package com.yichen.auth.config;

import com.yichen.auth.exception.MyBasicErrorController;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;
import java.util.List;

/**
 * spring boot 异常处理
 *
 * @author yichenshanren
 * @date 2017/11/29
 * @see ErrorMvcAutoConfiguration 根据这个来配置的
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
// Load before the main WebMvcAutoConfiguration so that the error View is available
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(ResourceProperties.class)
public class MyErrorMvcAutoConfig {

    private final ServerProperties serverProperties;

    private final List<ErrorViewResolver> errorViewResolvers;

    /**
     * @param serverProperties
     * @param errorViewResolversProvider
     * @author yichenshanren
     * @date 2017/11/29
     */
    public MyErrorMvcAutoConfig(ServerProperties serverProperties,
                                ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
        this.serverProperties = serverProperties;
        this.errorViewResolvers = errorViewResolversProvider.getIfAvailable();
    }

    /**
     * 配置自定义的 全局错误处理器
     * 替换默认的错误处理
     *
     * @param errorAttributes
     * @return
     * @author yichenshanren
     * @date 2017/11/29
     */
    @Bean
    public MyBasicErrorController basicErrorController(ErrorAttributes errorAttributes) {
        return new MyBasicErrorController(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }


}
