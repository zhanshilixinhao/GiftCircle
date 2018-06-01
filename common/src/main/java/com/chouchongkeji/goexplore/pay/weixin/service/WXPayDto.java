package com.chouchongkeji.goexplore.pay.weixin.service;

/**
 * @author yichenshanren
 * @date 2017/10/19
 */

public class WXPayDto {

    /**
     * 应用ID	        appid	String(32)	是	wx8888888888888888	微信开放平台审核通过的应用APPID
     商户号	            partnerid	String(32)	是	1900000109	微信支付分配的商户号
     预支付交易会话ID	prepayid	String(32)	是	WX1217752501201407033233368018	微信返回的支付交易会话ID
     扩展字段	        package	String(128)	是	Sign=WXPay	暂填写固定值Sign=WXPay
     随机字符串	        noncestr	String(32)	是	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
     时间戳	            timestamp	String(10)	是	1412000000	时间戳，请见接口规则-参数规定
     签名	            sign	String(32)	是	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法
     */

    private int code;
    private String prepay_id;
    private String appid;
    private String partnerid;
    private String packageValue;
    private String noncestr;
    private String sign;
    private String timestamp;
    private String message;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
