package com.yichen.auth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Spring security 属性配置
 *
 * @author yichenshanrenwqqwwq
 * @date 2017/11/26
 */
@ConfigurationProperties(prefix = "yichen.security")
public class SecurityProperties {

    private VerifyProperties smscode;

    private OAuth2Properties oauth2;

    private SignProperties sign;

    private ImageProperties image;

    public VerifyProperties getSmscode() {
        return smscode;
    }

    public void setSmscode(VerifyProperties smscode) {
        this.smscode = smscode;
    }

    public OAuth2Properties getOauth2() {
        return oauth2;
    }

    public void setOauth2(OAuth2Properties oauth2) {
        this.oauth2 = oauth2;
    }

    public SignProperties getSign() {
        return sign;
    }

    public void setSign(SignProperties sign) {
        this.sign = sign;
    }

    public ImageProperties getImage() {
        return image;
    }

    public void setImage(ImageProperties image) {
        this.image = image;
    }
}
