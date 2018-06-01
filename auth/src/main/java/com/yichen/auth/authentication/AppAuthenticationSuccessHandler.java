package com.yichen.auth.authentication;

import com.alibaba.fastjson.JSON;
import com.yichen.auth.SecurityConstants;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户名密码登录成功逻辑处理
 * 换取token
 *
 * @author yichenshanren
 * @date 2017/11/24
 */

@Component("appAuthenticationSuccessHandler")
public class AppAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices defaultAuthorizationServerTokenServices;
//
//    @Autowired
//    private UserDtailsCacheService userDtailsCacheService;

    /**
     * @param request        请求
     * @param response       响应
     * @param authentication 认证信息
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        /* 1.取出clientId和clientSecret */
        // 取出clientId
        logger.info("1.取出clientId和clientSecret ");
        String clientId = request.getParameter(SecurityConstants.CLIENT_ID);
        // 取出clientSecret
        String clientSecret = request.getParameter(SecurityConstants.CLIENT_SECRET);
        if (StringUtils.isAnyBlank(clientId, clientSecret)) {
            throw new UnapprovedClientAuthenticationException("app_id或app_secret缺失!");
        }
        /* 2.获取ClientDetails对象 */
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        // 判断clientDetails
        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException(SecurityConstants.CLIENT_ID + "不存在");
        }
        if (!StringUtils.equals(clientSecret, clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException(SecurityConstants.CLIENT_SECRET + "不匹配");
        }
        /* 3.构造TokenRequest */
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP,
                clientId,
                clientDetails.getScope(),
                SecurityConstants.GRANT_TYPE);
        /* 4.构造OAuth2Request */
        OAuth2Request auth2Request = tokenRequest.createOAuth2Request(clientDetails);
        /* 5.构造OAuth2Authentication */
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(auth2Request, authentication);
        /* 6.从AuthorizationServerTokenServices中获取token */
        OAuth2AccessToken token = defaultAuthorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        logger.info(token.toString());
        MyToken myToken = new MyToken(token);
//        userDtailsCacheService.updateCache(myToken, ((UserDetails)authentication.getPrincipal()).getUsername());
        logger.info("登录成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(ResponseFactory.sucData(myToken)));
    }
}
