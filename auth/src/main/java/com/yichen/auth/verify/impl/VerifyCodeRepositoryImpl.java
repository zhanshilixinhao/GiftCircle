package com.yichen.auth.verify.impl;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.goexplore.utils.K;
import com.yichen.auth.verify.VerifyCode;
import com.yichen.auth.verify.VerifyCodeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yichenshanren
 * @date 2017/11/28
 */
@Component("verifyCodeRepository")
public class VerifyCodeRepositoryImpl implements VerifyCodeRepository {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @param code 验证码
     * @param key  保存的key
     */
    @Override
    public void save(VerifyCode code, String key) {
        stringRedisTemplate.opsForValue().set(K.genKey(key, code.getType()),
                JSON.toJSONString(code), code.getExpire(), TimeUnit.SECONDS);
    }

    /**
     * @param key  保存的key
     * @param type 验证码类型
     * @return
     */
    @Override
    public VerifyCode get(String key, int type) {
        String code = stringRedisTemplate.opsForValue().get(K.genKey(key, type));
        if (StringUtils.isNotBlank(code)) {
            VerifyCode verifyCode = JSON.parseObject(code, VerifyCode.class);
            return verifyCode;
        }
        return null;
    }

    /**
     * @param key  保存的key
     * @param type 验证码类型
     */
    @Override
    public void remove(String key, int type) {
        stringRedisTemplate.delete(K.genKey(key, type));
    }
}
