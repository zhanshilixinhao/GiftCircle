package com.yichen.auth.verify;

import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.yichen.auth.service.SmsCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

/**
 * 短信验证码验证过滤器
 * 所有需要验证码的请求都应该经过此过滤器
 * OncePerRequestFilter只会被执行一次
 *
 * @author yichenshanren
 * @date 2017/11/26
 */
@Component
public class SmsCodeFilter extends OncePerRequestFilter {

    private static HashSet<String> URLSET = new HashSet<>();

    public static final String SMS_LOGIN = "/login/sms";
    public static final String SMS_REGISTER = "/noauth/user/register";
    public static final String SMS_BIND_OPENID = "/noauth/user/bind_openid";
    public static final String SMS_RESET_PWD = "/noauth/user/reset_pwd";

    static {
        URLSET.add(SMS_LOGIN);
        URLSET.add(SMS_REGISTER);
        URLSET.add(SMS_BIND_OPENID);
        URLSET.add(SMS_RESET_PWD);
    }

    @Autowired
    private AuthenticationFailureHandler appAuthenticationFailedHandler;

    @Autowired
    private SmsCodeService smsCodeService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (URLSET.contains(request.getRequestURI())) {
            try {
                verify(request);
            } catch (VerifyException ex) {
                appAuthenticationFailedHandler.onAuthenticationFailure(request, response, ex);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 校验验证码
     *
     * @param request
     * @author yichenshanren
     * @date 2017/11/26
     */
    private void verify(HttpServletRequest request) {
        // 如果不是post请求直接返回错误
        if (!StringUtils.equals(request.getMethod(), "POST")) {
            throw new VerifyException(ErrorCode.HTTP_METHOD_NOT_SUPPORT.getCode(), "此请求需要使用POST");
        }
        int type = 0;
        String uri = request.getRequestURI();
        if (StringUtils.equals(uri, SMS_REGISTER)) {
            type = 2;
        } else if (StringUtils.equals(uri, SMS_BIND_OPENID)) {
            type = 3;
        } else if (StringUtils.equals(uri, SMS_RESET_PWD)) {
            type = 4;
        } else if (StringUtils.equals(uri, SMS_LOGIN)) {
            type = 1;
        }
        // 获取验证码
        String code = request.getParameter("code");
        // 获取手机号
        String phone = request.getParameter("phone");
        // 获取验证码类型
 //       String typeStr = request.getParameter("type");
        // 判断参数是否为空
        if (StringUtils.isAnyBlank(code, phone)) {
            throw new VerifyException(ErrorCode.MISSING_PARAMETER.getCode(), "缺少参数");
        }
//        int type = 0;
//        // 判断验证码类型是不是数字
//        try {
//            type = Integer.parseInt(request.getParameter("type"));
//        } catch (NumberFormatException e) {
//            // 如果不是数字
//            throw new VerifyException(ErrorCode.SMS_CODE_ERROR.getCode(), "验证码类型必须是数字");
//        }
        // 验证验证码 逻辑
        Response response = smsCodeService.verifySmsCode(phone, type, code);
        // 验证码验证失败
        if (!response.isSuccessful()) {
            throw new VerifyException(response.getErrCode(), response.getMsg());
        }
    }
}
