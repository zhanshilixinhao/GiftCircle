package com.yichen.auth.mvc;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 处理客户端信息
 *
 * @author yichenshanren
 * @date 2017/12/14
 */
@Component
public class AppClientMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 处理AppClient这个注解
     *
     * @param methodParameter
     * @return
     * @author yichenshanren
     * @date 2017/12/14
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(AppClient.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest request,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        Integer client = (Integer) request.getAttribute("client", 0);
        if (client == null) {
            client = 0;
        }
        return client;
    }
}
