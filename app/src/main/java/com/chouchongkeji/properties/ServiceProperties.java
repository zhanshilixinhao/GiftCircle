package com.chouchongkeji.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 优咖 业务相关属性
 *
 * @author yichenshanren
 * @date 2017/12/4
 */
@Component
@ConfigurationProperties(prefix = "yichen.service")
public class ServiceProperties {

    // 趣探索logo的地址
    private String logo;

    private String host = "https://api.goexplore.io";

    private String productDetail;

    private String articleDetail;

    private String cardDetail;

    private String wxUrl = "noauth/pay/wx";

    private String aliUrl = "noauth/pay/aliv2";

    public String getArticleDetail() {
        return articleDetail;
    }

    public void setArticleDetail(String articleDetail) {
        this.articleDetail = articleDetail;
    }

    public String getLogo() { return logo; }

    public void setLogo(String logo) { this.logo = logo; }

    public String getWxUrl() {
        return wxUrl;
    }

    public void setWxUrl(String wxUrl) {
        this.wxUrl = wxUrl;
    }

    public String getAliUrl() {
        return aliUrl;
    }

    public void setAliUrl(String aliUrl) {
        this.aliUrl = aliUrl;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCardDetail() {
        return cardDetail;
    }

    public void setCardDetail(String cardDetail) {
        this.cardDetail = cardDetail;
    }
}
