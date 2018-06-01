package com.yichen.auth;

/**
 * 安全常量类
 *
 * @author yichenshanren
 * @date 2017/11/25
 */

public class SecurityConstants {

    // 参数中的client id
    public static final String CLIENT_ID = "app_id";
    // 参数中的client secret
    public static final String CLIENT_SECRET = "app_secret";
    // grant_type
    public static final String GRANT_TYPE = "goexplore";
    // openId
    public static final String OPEN_ID = "openId";
    // 手机号
    public static final String PHONE = "phone";
    // 密码
    public static final String PWD = "password";
    // 第三方账号类型
    public static final String ACC_TYPE = "accType";
    // 第三方登录请求url
    public static final String OPEN_ID_LOGIN_URL = "/login/third";
    // 短信验证码登录请求url
    public static final String SMS_LOGIN_URL = "/login/sms";
    // 手机号密码登录
    public static final String PHONE_LOGIN_URL = "/login/phone";
    // 手机号密码登录 跳转url
    public static final String PHONE_LOGIN_PAGE_URL = "/login/page";
    // 验证码请求url
    public static final String ASK_CODE = "/ask/code";
    // 不需要认证的请求
    public static final String NOAUTH_URL = "/noauth/**";

    public static final String FAKE_URL = "/fauth/**";
    public static final String DRUID = "/druid/**";
}
