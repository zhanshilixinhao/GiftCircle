package com.yichen.auth.controller;

import com.yichen.auth.SecurityConstants;
import com.yichen.auth.authentication.MyToken;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.yichen.auth.service.UserDtailsCacheService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 刷新token
 *
 * @author yichen
 * @date 2017/12/03
 */
@RestController
public class TokenRefreshController {

    private Logger logger = LoggerFactory.getLogger(TokenRefreshController.class);

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices defaultAuthorizationServerTokenServices;

    @Autowired
    private UserDtailsCacheService userDtailsCacheService;

    @PostMapping("token/refresh")
    public Response refresh(@RequestParam("app_id") String appId,
                            @RequestParam("app_secret") String appSecret,
                            @RequestParam("refresh_token") String refreshToken) {
        logger.info(appId + "||" + appSecret + "||" + refreshToken);
        // 校验必传参数
        if (StringUtils.isAnyBlank(appId, appSecret, refreshToken)) {
            return ResponseFactory.errMissingParameter();
        }
        // 获取Client信息
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(appId);
        if (clientDetails == null) {
            return ResponseFactory.errMsg(ErrorCode.CLIENT_NOT_EXIST.getCode(), "client不存在!");
        }
        // 判断secret是否匹配
        if (!StringUtils.equals(appSecret, clientDetails.getClientSecret())) {
            return ResponseFactory.errMsg(ErrorCode.CLIENT_NOT_EXIST.getCode(), "secret不匹配!");
        }
         /* 3.构造TokenRequest */
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP,
                appId,
                clientDetails.getScope(),
                SecurityConstants.GRANT_TYPE);
        /* 5.从AuthorizationServerTokenServices中获取刷新token */
        try {
            OAuth2AccessToken token = defaultAuthorizationServerTokenServices.refreshAccessToken(refreshToken, tokenRequest);
            MyToken myToken = new MyToken(token);
//        userDtailsCacheService.updateCache(myToken, );
            return ResponseFactory.sucData(myToken);
        } catch (OAuth2Exception e) {
            return ResponseFactory.errMsg(ErrorCode.INVALID_REFRESH_TOKEN.getCode(), "refresh_token无效");
        }
    }

}