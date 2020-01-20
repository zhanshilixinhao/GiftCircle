package com.chouchongkeji.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

/**
 * @author linqin
 * @description log拦截器
 * @date 2020/1/20
 */
@Component
@Order(-1)
public class LogFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String traceId = UUID.randomUUID().toString();
        // 记录下请求内容
        logger.info("traceId:{}, URL:{}",traceId,request.getRequestURL().toString());
        logger.info("traceId:{},HTTP_METHOD :{}",traceId,request.getMethod());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            logger.info("traceId:{},{}={}",traceId, name, request.getParameter(name));
        }
        ContentCachingResponseWrapper content = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(request,content);
        byte[] contentAsByteArray = content.getContentAsByteArray();
        String body = new String(contentAsByteArray);
        response.getWriter().write(body);
        logger.info("traceId:{},body:{}",traceId,body);
    }
}
