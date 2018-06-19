package com.yichen.auth.exception;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.OAuth2ExceptionRenderer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * spring oauth2 异常处理 配置
 *
 * @author yichenshanren
 * @date 2017/11/29
 */
@Component
public class HeaderOnlyOAuth2ExceptionRenderer implements OAuth2ExceptionRenderer {

    private final Logger logger = LoggerFactory.getLogger(HeaderOnlyOAuth2ExceptionRenderer.class);

    /**
     * 处理认证过程中的错误 返回
     *
     * @param responseEntity 错误实体对象
     * @param webRequest     servlet
     * @throws Exception
     */
    public void handleHttpEntityResponse(HttpEntity<?> responseEntity,
                                         ServletWebRequest webRequest) throws Exception {
        if (responseEntity == null) {
            return;
        }
        /* 替换oauth2默认的错误处理返回结果 */
        try {
            // 将body强转 OAuth2Exception 如果错误 在catch中处理
            OAuth2Exception exception = (OAuth2Exception) responseEntity.getBody();
            StringBuilder sb = new StringBuilder();
            // 将错误信息中的”和等号替换
            sb.append(exception.getSummary().replaceAll("\"", "").replaceAll("=", "："));
            // 构造自定义的 响应内容
            Response response = ResponseFactory.errMsg(exception.getHttpErrorCode(), sb.toString());
            // content type
            webRequest.getResponse().setContentType("application/json;charset=UTF-8");
            webRequest.getResponse().getWriter().write(JSON.toJSONString(response));
        } catch (Throwable e) {
            webRequest.getResponse().setContentType("application/json;charset=UTF-8");
            webRequest.getResponse().getWriter().write(JSON.toJSONString(ResponseFactory.err(e.getMessage())));
        }
    }

    private HttpInputMessage createHttpInputMessage(NativeWebRequest webRequest)
            throws Exception {
        HttpServletRequest servletRequest = webRequest
                .getNativeRequest(HttpServletRequest.class);
        logger.info("createHttpInputMessage...");
        return new ServletServerHttpRequest(servletRequest);
    }

    private HttpOutputMessage createHttpOutputMessage(
            NativeWebRequest webRequest) throws Exception {
        HttpServletResponse servletResponse = (HttpServletResponse) webRequest
                .getNativeResponse();
        logger.info("createHttpOutputMessage...");
        return new ServletServerHttpResponse(servletResponse);
    }
}