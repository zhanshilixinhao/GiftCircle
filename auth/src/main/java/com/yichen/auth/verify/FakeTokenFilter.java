package com.yichen.auth.verify;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetailsSource;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 伪造的token过滤器
 *
 * @author yichenshanren
 * @date 2018/1/11
 */

public class FakeTokenFilter extends OncePerRequestFilter {

    private String FAKE_TOKEN = "fake_token";
    private String FAKE_URI_START = "/f";

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new OAuth2AuthenticationDetailsSource();

    private OAuth2AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if (request.getRequestURI().startsWith(FAKE_URI_START)) {
//            System.out.println("艾弗森的广泛大概");
            // 判断请求中是否带有token
            String token = request.getParameter(FAKE_TOKEN);
            // 如果没有token
            if (!StringUtils.isBlank(token)) {
                try {
                    // 获取认证信息
                    PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(token, "");

                    request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE, authentication.getPrincipal());
                    AbstractAuthenticationToken needsDetails = authentication;
                    needsDetails.setDetails(authenticationDetailsSource.buildDetails(request));

                    Authentication authResult = authenticationManager.authenticate(authentication);

                    SecurityContextHolder.getContext().setAuthentication(authResult);

                } catch (OAuth2Exception failed) {
                    SecurityContextHolder.clearContext();
                }
            }
        }
        chain.doFilter(request, response);
    }

    public void setAuthenticationManager(OAuth2AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
