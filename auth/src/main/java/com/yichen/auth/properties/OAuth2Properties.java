package com.yichen.auth.properties;

/**
 * 配置多组客户端信息
 *
 * @author yichenshanren
 * @date 2017/12/1
 */

public class OAuth2Properties {

    private OAuth2ClientProperties[] client;

    public OAuth2ClientProperties[] getClient() {
        return client;
    }

    public void setClient(OAuth2ClientProperties[] client) {
        this.client = client;
    }
}
