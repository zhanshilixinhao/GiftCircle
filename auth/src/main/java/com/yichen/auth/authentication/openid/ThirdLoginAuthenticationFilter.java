package com.yichen.auth.authentication.openid;

import com.yichen.auth.SecurityConstants;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.yichen.auth.verify.VerifyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 第三方登录认证请求过滤器
 *
 * @author yichenshanren
 * @date 2017/11/30
 */

public class ThirdLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String openIdParamter = SecurityConstants.OPEN_ID;
    private String accTypeParamter = SecurityConstants.ACC_TYPE;

    private boolean postOnly = true;

    public ThirdLoginAuthenticationFilter() {
        // 只拦截第三方登录请求
        super(new AntPathRequestMatcher(SecurityConstants.OPEN_ID_LOGIN_URL, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        // 如果不是post请求直接 返回
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String openId = this.obtainopenId(request);
            openId = openId.trim();
            int type = this.obtainAccType(request);
            // 创建token
            ThirdLoginAuthenticationToken authRequest = new ThirdLoginAuthenticationToken(openId, type);
            this.setDetails(request, authRequest);
            // 调用AuthenticationManager
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected void setDetails(HttpServletRequest request, ThirdLoginAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    /**
     * 从请求中获取 账号类型参数
     *
     * @param request
     * @return
     * @author yichenshanren
     * @date 2017/11/30
     */
    private int obtainAccType(HttpServletRequest request) {
        String typeStr = request.getParameter(accTypeParamter);
        if (StringUtils.isBlank(typeStr)) {
            throw new VerifyException(ErrorCode.MISSING_PARAMETER.getCode(), "缺少第三方账号类型!");
        }
        try {
            int type = Integer.parseInt(typeStr);
            return type;
        } catch (NumberFormatException e) {
            throw new VerifyException(1, "账号类型错误");
        }
    }

    /**
     * 获取openId参数值
     *
     * @param request
     * @return
     * @author yichenshanren
     * @date 2017/11/30
     */
    private String obtainopenId(HttpServletRequest request) {
        String openId = request.getParameter(openIdParamter);
        if(StringUtils.isBlank(openId)) {
            throw new VerifyException(ErrorCode.MISSING_PARAMETER.getCode(), "缺少openId!");
        }
        return openId;
    }
}

