package com.chouchongkeji.goexplore.pay.alipay_v2;

/**
 * @author yichenshanren
 * @date 2017/11/1
 */

public class ConfigV2 {

    // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static final String partner = "2088911647939755";
    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串
    public static final String seller_id = partner;
    // 商户的私钥
    public static final String private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC34HkWWxIu3prJ0U/VYPpeDEYrD603xFWdUiesn18AwX/xAcdlLgxeX5XfNfZGXRbo7IuOK+WIGtkpcFWDts3O1zumjpLrxTtxx4mX0NFAYcJ2pUOLOc9+0e4/6A431HTEwlU1DVR6thupaqwx1rT0yad/9UqpYL2h5kCj61jRQeSM2IYL4YpFnmcjIjWPDu0Mo1OkY8MIcL64xtCucLWtJePDfHHZ3UQeOotJJRxQwQviRh+8+L3ZdedAYUSbxw012Ig7r62LAfpVCNgNwTs7FF75Ko9zodEQrMn7zmNAQSvIOxXZjNTxpIh665e8AF+bEJ/C8xNYoo/t1XIQzfwJAgMBAAECggEBALOSqk6V5D0Xsq00GDY3/mrIq+Iz7iOPP1ljuVFRV1OCpbM13IhIn1WvHcuehIQVN2GEWYof/oqcRca9aRY3+ehttFil1oAg3zvv9cTo7uJkVqjOHxeh/q1I0ddhhjkp6FubueEUwpMQtBURE1ec6p5BLa6+XArsamXpWul61wOMHuNouReDkCgJCEajGqiX5uucX3/KWB9a96o6+LlG3yUQmnEq32v+IqLpyZRwNgRkMVCOLW7/F3z/DAXzSJ6/bJPfz9ce+ZM3+oyt+98z3jOjkR4MozS9OcE/zOclXq5s3SbZSe63LW0r/NK8vT2lGR4mp6CDS+VN5w2r0S7YBHECgYEA8yjQm662Q5p3caJiOY80J6qenD6VVuqdmIN0New98+4vRi1G3UBlau8t4weOX3dMAzx2QwigGqMGgdxSFRvVLyxRtkd+Jc9ehJhqk4OyB9r6SmD71Z3rHyEaLGTeN0ZBEOjeH9l3DVoLRKy4ZM/s20QX9+ooR6oKGrJxdTn1bCUCgYEAwZY89YCh/OCsRHsFS8LKi7UhHVggqrTu8JsjrMbZANrxavQ8UmEOe5iSIAF058wJdcJ05wX23TvK8ZWUa8fkq6+2mmBy+viFfYB1e8KMvb4tqyMOlySXnEyHtyokZeUJA6WRbWcYhubdhY0uSIHzrOadDYaBHAUUjgAjS5bCmRUCgYARcwldUrFTsp8FsZB47vTgL9dIARm4bge3LR/Gytg7TAlqcM9YC0n73YQeealW4nm69bjK4LCUmMRbx1LAnwmyY+zNNt4vmmz++O0U/utksuspMUKBNt7rkfWN4AMuVkVPEFb4VCO9+os6d2aYHW+y4HUT3mLbmbE17z5DGlCpXQKBgQCGxEh7wKkEmoRSyDHPpDPNPrNm8ksLjBordlVHmsbHSaR3iL/VuauTb4uKrHjuRaTwTgeELSrGqTGHLVK+FdKjkxfUMQDaptiHnj4rgizoitTQJv+pSNgi/0Qbx0K/jMrRtxmex/7TKzVcHAT0hUNWQtVi7RhEoWTF1mK75KBkYQKBgBJxSLvr6V1eWKp8Cyd3HQzJvSEYKcXRwcegn1EHzK0ECaxMf6IB57Al2EnrnY/M+vHd/qYruZqNpMNt2ScXjNaq3vXQM8jiQ1lvOeKYo42oPbJZ6C6du2cNLJO4JrMi9zr0o1XaUNKJQML0KC8DS9ux4Ra92FdyKcnU2vO2Dpkf";
    // 支付宝的公钥，无需修改该值
    public static final String ali_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiGUZvL3aG915aa8rWLTUYwSJAqUygYUYCd4KRObR4NgDaGrZJ3Qgm9jCLDR5iDqLQbz+fKuJs6kVzPZ7XN3xbGuPoina1ob26PpTc4GspyN3XKUdOxpGIT1k3XKkuKv81jnS3xHhI52cfu3kgXQnFFH3m2GZxdZ3bLYt27fH4KDUibiRgGeA/rKUoe/q6TpDCPLqRCqcWs8/9p7USpk1bZbbnMkgL/7Oe9LGyAf96caU+RmQYQbSoMT0wVw0Q/15mvpS9AhBL3OHFEhn99idOYmaERkpeYvsOfcA4N/7eM2ncvdQeqTLOa8jPVeI8oqweSX3K91CwXinGLfA/HjljwIDAQAB";
    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    // 调试用，创建TXT日志文件夹路径
    public static final String log_path = "/data/payLogs/";
    // 字符编码格式 目前支持 gbk 或 utf-8
    public static final String input_charset = "utf-8";
    // 签名方式 不需修改
    public static final String sign_type = "RSA2";
    // 应用id
    public static final String APP_ID = "2018020602151503";


}
