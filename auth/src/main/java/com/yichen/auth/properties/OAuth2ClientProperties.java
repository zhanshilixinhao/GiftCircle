package com.yichen.auth.properties;

/**
 * oauth2 client配置信息
 *
 * @author yichenshanren
 * @date 2017/12/1
 */

public class OAuth2ClientProperties {

    private String clientId;

    private String clientSecret;

    // 访问令牌过期时间 默认 3600秒
    private int accessTokenValidityTime = 90 * 24 * 3600;
    // 默认90天
    private int refreshTokenValidityTime = 180 * 24 * 3600;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public int getAccessTokenValidityTime() {
        return accessTokenValidityTime;
    }

    public void setAccessTokenValidityTime(int accessTokenValidityTime) {
        this.accessTokenValidityTime = accessTokenValidityTime;
    }

    public int getRefreshTokenValidityTime() {
        return refreshTokenValidityTime;
    }

    public void setRefreshTokenValidityTime(int refreshTokenValidityTime) {
        this.refreshTokenValidityTime = refreshTokenValidityTime;
    }
}
