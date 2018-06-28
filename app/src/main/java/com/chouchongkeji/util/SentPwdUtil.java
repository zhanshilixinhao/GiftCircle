package com.chouchongkeji.util;

import com.chouchongkeji.goexplore.utils.AESUtils;
import com.chouchongkeji.goexplore.utils.Utils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yichenshanren
 * @date 2018/6/19
 */

public class SentPwdUtil {

    public static String decrypt(String de, String s1, String apiKey, String time) {
        // 构造加密密钥
        apiKey = genKey(apiKey, time, s1);
        String decry = AESUtils.decrypt(apiKey, de);
        if (StringUtils.isBlank(decry)) return null;
        // 取出用户设置的密码
        String[] split = decry.split("@");
        if (split.length != 2) {
            return null;
        }
        if (!StringUtils.equals(split[1], Utils.toMD5(String.format("%spinjie%s", time, s1)))) {
            return null;
        }
        return split[0];
    }

    public static String genKey(String apiKey, String time, String s1) {
        int len = apiKey.length();
        // 取出随机数字的第一位
        int first = s1.charAt(0) - 48;
        // 去除apiKey中的第first位
        apiKey = String.format("%s%s%s%s", time, apiKey.substring(0, first),
                apiKey.substring(first < len ? first + 1 : first, len), s1);
        return apiKey;
    }

    public static String encrypt(String apiKey, String pwd, String s1, String time) {
        pwd = Utils.toMD5(pwd);
        pwd = String.format("%s@%s", pwd, Utils.toMD5(String.format("%spinjie%s", time, s1)));
        apiKey = genKey(apiKey, time, s1);
        return AESUtils.encrypt(apiKey, pwd);
    }

}
