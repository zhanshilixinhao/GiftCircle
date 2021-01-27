package com.yichen.auth.authentication;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 定义自己的token信息
 *
 * @author yichenshanren
 * @date 2017/11/25
 */

public class MyToken implements Serializable {

    @JSONField(name = "access_token")
    @JsonProperty("access_token")
    private String accessToken;
    @JSONField(name = "refresh_token")
    @JsonProperty("refresh_token")
    private String refreshToken;
    // token过期剩余时间
    private long expire;

    private BigDecimal rebate;

    private BigDecimal userRebate;



    public MyToken() {
    }

    public MyToken(OAuth2AccessToken token) {
        this.accessToken = token.getValue();
        this.refreshToken = token.getRefreshToken().getValue();
        this.expire = token.getExpiresIn();
    }

    public MyToken(String accessToken, String refreshToken,long expire,BigDecimal rebate,BigDecimal userRebate) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expire = expire;
        this.rebate = rebate;
        this.userRebate = userRebate;
    }

    public BigDecimal getRebate(){
        return rebate;
    }

    public void setRebate(BigDecimal rebate){
        this.rebate = rebate;
    }
    public BigDecimal getUserRebate(){
        return userRebate;
    }

    public void setUserRebate(BigDecimal userRebate){
        this.userRebate = userRebate;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}
