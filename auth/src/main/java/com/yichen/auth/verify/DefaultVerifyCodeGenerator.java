package com.yichen.auth.verify;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * 默认的验证码生成实现
 *
 * @author yichenshanren
 * @date 2017/11/25
 */
@Component
public class DefaultVerifyCodeGenerator implements VerifyCodeGenerator {

    /**
     * 生成验证码
     *
     * @param type   验证码类型
     * @param expire 过期时间
     * @return 验证码
     * @author yichenshanren
     * @date 2017/11/25
     */
    @Override
    public VerifyCode generate(int type, long expire) {
        String code = RandomStringUtils.randomNumeric(6);
        return new VerifyCode(type, code, expire);
    }


}
