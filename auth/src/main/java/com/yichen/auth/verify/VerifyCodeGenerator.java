package com.yichen.auth.verify;

/**
 * 验证码生成器
 *
 * @author yichenshanren
 * @date 2017/11/25
 */

public interface VerifyCodeGenerator {

    VerifyCode generate(int type, long expire);

}
