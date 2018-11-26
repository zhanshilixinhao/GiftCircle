package com.chouchongkeji.util;

import com.chouchongkeji.goexplore.utils.AESUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @author yichen
 * @date 18-11-23 下午1:08
 **/

public class UserInfoUtils {

    private static final String SEED = "EQiv48DGPuxpeRKg";

    public static String encrypt(Integer userId) {
        return AESUtils.encrypt(SEED, String.format("%s,%s", userId, UUID.randomUUID().toString()));
    }

    public static Integer decrypt(String text) {
        String decrypt = AESUtils.decrypt(SEED, text);
        if (StringUtils.isBlank(decrypt)) return null;
        try {
            return Integer.parseInt(decrypt.split(",")[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
