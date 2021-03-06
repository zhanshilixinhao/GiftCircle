package com.chouchongkeji.util;

import com.chouchongkeji.goexplore.pay.weixin.common.Util;
import com.chouchongkeji.goexplore.utils.AESUtils;
import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.RSAProvider;
import com.chouchongkeji.goexplore.utils.Utils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yichenshanren
 * @date 2018/6/19
 */

public class SentPwdUtil {

    public static String decrypt(String de, String s1, String apiKey, String time) {
        String decry = null;
        if (StringUtils.equals(ApiSignUtil.IOS, apiKey)) {
            decry = RSAProvider.decrypt(de);
        } else {
            // 构造加密密钥
            apiKey = genKey(apiKey, time, s1);
            decry = AESUtils.decrypt(apiKey, de);
        }
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

    public static String encryptRSA(String apiKey, String pwd, String s1, String time) {
        pwd = Utils.toMD5(pwd);
        pwd = String.format("%s@%s", pwd, Utils.toMD5(String.format("%spinjie%s", time, s1)));
        return RSAProvider.encrypt(pwd);
    }

    public static void main(String[] args) {
        System.out.println(Utils.toMD5("123456"));
//        String rsa = encryptRSA(ApiSignUtil.IOS, "123456", "30436944489648177637848285958404", "VNZABWQQJKUNRIPHVCCUWWRRGPADZVBY");
        String rsa = "EEIawcrmmC3SlnXrh265yNx8qbE4WQ/2+/f0qbL6wTt0e3JsRm69oXZoiYUOvdqEwTaFnofP63NxSGeN8LStzF97U4P1UQ4XIvLfXXza6qBOgZXdH7i5weG8S1fp4obqmzTyNePK/LP5nOQ4WxkdDwpc0SAVFPWqkn3vr6S+T7iRnDi/H5hXdqaPmXvepX3KiDbbWnXH/peWcYGCk7WmNMzOYdl1T69QxWo7Fp+lDPjVlpa6jJncf7u/6EkyWGx6G97m/o6ab2IUmEuwehay8tckFoXYLlMCitHb2CXtxi/a21CO0Y71y7oWTsXljye8NOYZnoA3l2SXKRYx49gCuQ==";
        System.out.println(rsa);
        String decrypt = decrypt(rsa, "30436944489648177637848285958404", ApiSignUtil.IOS, "VNZABWQQJKUNRIPHVCCUWWRRGPADZVBY");
        System.out.println(decrypt);
        System.out.println(RSAProvider.encrypt("E10ADC3949BA59ABBE56E057F20F883E@1F27A9443F2617A5535E5A904540CC53"));
    }


}
