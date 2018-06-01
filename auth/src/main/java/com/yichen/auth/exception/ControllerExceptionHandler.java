package com.yichen.auth.exception;

import com.chouchongkeji.goexplore.common.ErrCodeException;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yichenshanren
 * @date 2017/11/24
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response exception(Exception ex) {
        ex.printStackTrace();
        if (ex instanceof ErrCodeException) {
            return ResponseFactory.errMsg(((ErrCodeException) ex).getErrCode(), ex.getMessage());
        }
        return ResponseFactory.err(String.format("请求异常%s:错误信息--%s", ex.getClass().getName(), ex.getMessage()));
    }


}
