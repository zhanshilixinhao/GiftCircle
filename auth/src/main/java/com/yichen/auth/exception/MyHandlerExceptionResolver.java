package com.yichen.auth.exception;

import com.alibaba.fastjson.JSONObject;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {

        /** 设置状态码--统一成功 **/
        response.setStatus(HttpStatus.OK.value());
        /** 设置ContentType json **/
        response.setContentType("application/json");
        /** 设置编码 **/
        response.setCharacterEncoding("UTF-8");
        /** 设置返回头 **/
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        /** 统一异常返回 **/
        String message = e.getMessage() ;
        int code = 1;

        Response re = ResponseFactory.errMsg(code, message);
        /** 调试模式 **/
        e.printStackTrace();
        try {
            response.getWriter().write(JSONObject.toJSONString(re));
        } catch (IOException ex) {

        }
        return new ModelAndView();
    }
}