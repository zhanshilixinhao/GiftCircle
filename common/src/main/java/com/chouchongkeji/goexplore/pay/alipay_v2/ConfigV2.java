package com.chouchongkeji.goexplore.pay.alipay_v2;

/**
 * @author yichenshanren
 * @date 2017/11/1
 */

public class ConfigV2 {

    // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static final String partner = "2088231386393754";
    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串
    public static final String seller_id = partner;
    // 商户的私钥
    public static final String private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCAXkudhFjlv3mMXKPhVwd5iyWXgOPT5xZ6hlbGl4XHpMg46Jyu4JM6qDOb5AagRbmbbyYZNiGpS3zlcqXsQofxOO00Jrtx4oSgsEhc9MSDbFdxJG3McyAz9jq4Pqi2rd5avPuqeHbVbMWfo+6EHEfoIacIMQvFsRpODauOy9fAiWkpoqCk6i+jId6wyRrmt2ExlKF+QgETcJH2v3/2TcyRJM6PWVM1ft2ZM93BAgR9Y8Y8vCKuF390IWtgpeF9c24vJ2un0d0Q0P3GO2TlzbdSPHmtPlmkKyr+y7Z/pueUiMRvSD+oWBF5ovdQ4iTrwXbyedmIfLAS4RIgJvuhQ6EJAgMBAAECggEAS3UihclSaFsRnAyo2MFLx4qf4lsFkX55nDRztfFD1oNqnuk9kR657PUKwUxdCMFzxYakopVLXDTfihDGsAa72nRYGxvZe6xnwf1dzCaljYuWKw/66v+RonnPMpbCnf++gSAt35tIX1S9JoY6orXtHusJQKtz/BWLHO60NhBlFuvaAU6jM9jq/uAcz3HuJld0Ka4EMsOcOcCe6UxH3ABN5ZlroyB9xjxWSlP7VDeac3w+omdriDJKe9r9DSzrEXPQ+WEOPKdh3KKN5fzQRjt/4/hnKT7Bk7ryH7QfA8x9WPd5aMOocfZ4GUEIRXfL7yMM+uoKMrLtuysbokMRdkAWAQKBgQC7qdLChIAUrAoV9V5UoeE7u1hUoR0sd5dGcUdkrfrCRd0We8i78tQFou5tDq4y4TiUQ3hmBQHYs2RhNQRdbGS44kIRDtA65Qyq9z9PNOjjJZQgBaDnjxSHfhxkSmIQIdb/qzy6JCOi087d+hXwwCVtQIoiZAwacmZ/8XHF6YSUcQKBgQCvHO1Q3Aw6wduAOPd7mf2nx7fIRgGrt+mceUzufe/T8z+8kKB0IQdQmD1gDkBDnwxSHnAB/GB1Z5OYHGnhbelmMBhrEQIRkjTnLdw50hhGcu91r8M5jhS2aR6LOXjTelFRTA1SLZXIu2eV/I4noZvMQPKhxDWtsfahuR90i8xCGQKBgQChzDFidErZh9rKQ9sorMk8brIhhSdaB2Ht4gzjyPzCMuoDUIYpRZt3oJpgfJ/ETloxulo722VUKJcLGGLDCYltveWoP1VY8dKSNMnoyH1iGiwzz6aC/uF8UEa6UUdrEUIE/OZGtpHi+SsKR2xTPdcG8vYl4Ixu4ONuvAST1lnwgQKBgFCjLnk0y4DnMlAKa5SEZfU0F+JmsnBD4ZTHmvCcF6tvcWNA6lJvvzGS2Xh5N4isHf1WvNd9Q2HtD19VfkBr37XJ4gSzsVMsE6Z9TNNndvxCAxkblffUAJ+7KnCMR6KCyh14uw2jpjzoZ5v+z+taFSdqpRBvDRTuRZdVazG9CpNJAoGAO57WVDMkfW+HDCKHY4/5xdY52zIvRPvUorDhB5+ihHIeoicyGa1O3nMDBhQUXg3C8w9JRNPHN4zmpx7rXAYgBledP4BRbEiX/bixQSJK7YcUd1ckjcGkAAIB1vFUfq6J7Ql77scLJr7A868aHVQbtMK0jfWiXsvg68n+dYTBiwo=";
    // 支付宝的公钥，无需修改该值
//    public static final String ali_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlVIBUElBTBojrO57z2u/xhHwiF7C0ausIP4QkwkjW/ySARv0ksj14Lw4fXhzkHPT2KmrlhqaLVlmicUcdu3OJs3we4UvCWvnk90lFDTua61EYNUoUauJhoPT89MTzMtOajSRgqESw8501No3ACY3qm1E6G4NLdjqaBFanMuu84liBjGtUXXyMtvV1j4wsBkKkV6nEZ6ajckD8Xa+IUK4H0clK0gOwq8PYYed7fOq0BB+zlLNUR5ghETUYF0gjZyNskTzMc5wWhS1Bmq+PLCV5Fko34Po0igepnLddVWuqp2z+18IukuJbYpGLcyvpqxh13Fi8MdVOE4znOgb2kMklwIDAQAB";
    public static final String ali_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlVIBUElBTBojrO57z2u/xhHwiF7C0ausIP4QkwkjW/ySARv0ksj14Lw4fXhzkHPT2KmrlhqaLVlmicUcdu3OJs3we4UvCWvnk90lFDTua61EYNUoUauJhoPT89MTzMtOajSRgqESw8501No3ACY3qm1E6G4NLdjqaBFanMuu84liBjGtUXXyMtvV1j4wsBkKkV6nEZ6ajckD8Xa+IUK4H0clK0gOwq8PYYed7fOq0BB+zlLNUR5ghETUYF0gjZyNskTzMc5wWhS1Bmq+PLCV5Fko34Po0igepnLddVWuqp2z+18IukuJbYpGLcyvpqxh13Fi8MdVOE4znOgb2kMklwIDAQAB";

    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    // 调试用，创建TXT日志文件夹路径
    public static final String log_path = "/data/payLogs/";
    // 字符编码格式 目前支持 gbk 或 utf-8
    public static final String input_charset = "utf-8";
    // 签名方式 不需修改
    public static final String sign_type = "RSA2";
    // 应用id
    public static final String APP_ID = "2018082361125281";


}
