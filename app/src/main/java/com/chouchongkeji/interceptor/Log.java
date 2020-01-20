package com.chouchongkeji.interceptor;

import com.gexin.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.UUID;

/**
 * @author linqin
 * @description log 拦截器
 * @date 2020/1/17
 */
//@Aspect
//@Component
public class Log{

    private Logger logger = LoggerFactory.getLogger(Log.class);


    @Pointcut("execution(public * com.chouchongkeji.mvc.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * 拦截实现 log
     * @return
     */
    @Around("webLog()")
    public Object interceptWebLog(ProceedingJoinPoint point) throws Throwable {
        // 接收到请求,请求记录内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String traceId = UUID.randomUUID().toString();
        // 记录下请求内容
        logger.info("traceId:{}, URL:{}",traceId,request.getRequestURL().toString());
        logger.info("traceId:{},HTTP_METHOD :{}",traceId,request.getMethod());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            logger.info("traceId:{},{}={}",traceId, name, request.getParameter(name));
        }
        Object proceed = null;
        try {
           proceed = point.proceed();
           logger.info("traceId:{}, response:{}",traceId, JSON.toJSONString(proceed));
        } catch (Throwable throwable) {
            logger.info("traceId:{}, exception:{}",traceId,throwable.getMessage());
            throw throwable;
        }
        return proceed;
    }

}
