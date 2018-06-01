package com.yichen.auth.verify;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * @author yichenshanren
 * @date 2018/2/4
 */

@Component
public class TokenAuth {

    private OAuth2AuthenticationManager auth2AuthenticationManager;

    @Autowired
    public TokenAuth(TokenStore redisTokenStore) {
        // 处理access_token认证管理器
        auth2AuthenticationManager = new OAuth2AuthenticationManager();
        // token处理
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        // token存储器
        defaultTokenServices.setTokenStore(redisTokenStore);
        auth2AuthenticationManager.setTokenServices(defaultTokenServices);
    }

    public Response auth(String token) {
        try {
            // 获取认证信息
            PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(token, "");

            Authentication authResult = auth2AuthenticationManager.authenticate(authentication);

            SecurityContextHolder.getContext().setAuthentication(authResult);

            Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            System.out.println(o);
            return ResponseFactory.sucData(o);
        } catch (OAuth2Exception failed) {
            SecurityContextHolder.clearContext();
//            System.out.println(failed);
            return ResponseFactory.errMsg(failed.getHttpErrorCode(), failed.getOAuth2ErrorCode() + failed.getSummary());
        }
    }


}
