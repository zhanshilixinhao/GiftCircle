package com.yichen.auth.authentication;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.goexplore.common.ErrCodeException;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录认证失败处理类
 *
 * @author yichenshanren
 * @date 2017/11/24
 */
@Component("appAuthenticationFailedHandler")
public class AppAuthenticationFailedHandler implements AuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        logger.info(e.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        Response res ;
        logger.info(e.getClass().getSimpleName());
        if (e instanceof ErrCodeException) {
            res = ResponseFactory.errMsg(((ErrCodeException) e).getErrCode(), e.getMessage());
        } else if (e instanceof BadCredentialsException){
            res = ResponseFactory.err("用户名或密码错误!");
        } else {
            res = ResponseFactory.err(e.getMessage());
        }
        response.getWriter().write(JSON.toJSONString(res));
    }
}
