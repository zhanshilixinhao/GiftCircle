package com.chouchongkeji.goexplore.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yichenshanren
 * @date 2017/12/20
 */

public class ApiSignUtil {

    public static final String SIGN = "sign";
    public static final String TIME = "time";

    public static final String ANDROID = "S#2LVjx*lZNVVR3H7#Qiz^MnD$EqZL4V";
    public static final String IOS = "Eo&ISlQb2Knp8^1^C3x0NHDk%#VtWw27";
    public static final String WXMINI = "dZ0E^z!YN6RLu2vs6bX4#s#R5Xrfc2pv";

    /**
     * 获取请求中除了签名之外的参数
     *
     * @param request 请求
     * @return
     * @author yichenshanren
     * @date 2017/12/20
     */
    public static Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> enumeration = request.getParameterNames();
        String name;
        while (enumeration.hasMoreElements()) {
            name = enumeration.nextElement();
//                System.out.println(name+"="+request.getParameter(name));
            if (!StringUtils.equals(name, SIGN)) {
                map.put(name, request.getParameter(name));
            }
        }
        return map;
    }

    /**
     * 创建参数签名
     *
     * @param keys 签名密钥
     * @param map  参数
     * @return string 签名值
     * @author yichenshanren
     * @date 2017/12/20
     */
    public static Map<String, String> sign(Map<String, String> map, String... keys) {
        String[] paramArr = new String[map.size()];
        int i = 0;
        for (Map.Entry<String, String> e : map.entrySet()) {
            paramArr[i] = String.format("%s=%s", e.getKey(), e.getValue());
            i++;
        }
        Arrays.sort(paramArr, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (String param : paramArr) {
            sb.append(param).append("&");
        }
        String before = sb.toString();
        Map<String, String> result = new HashMap<>();
        for (String key : keys) {
            sb.delete(0, sb.length());
            sb.append(before).append("key=").append(key);
            result.put(key, Utils.toMD5(sb.toString()));
        }
        return result;
    }

    public static Map<String, String> sign1(Map<String, Object> map, String... keys) {
        String[] paramArr = new String[map.size()];
        int i = 0;
        for (Map.Entry<String, Object> e : map.entrySet()) {
            paramArr[i] = String.format("%s=%s", e.getKey(), e.getValue());
            i++;
        }
        Arrays.sort(paramArr, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (String param : paramArr) {
            sb.append(param).append("&");
        }
        String before = sb.toString();
        Map<String, String> result = new HashMap<>();
        for (String key : keys) {
            sb.delete(0, sb.length());
            sb.append(before).append("key=").append(key);
            System.out.println(sb.toString());
            result.put(key, Utils.toMD5(sb.toString()));
        }
        return result;
    }

    /**
     * 校验两个签名是否一致
     *
     * @return
     */
    public static int validateSign(String sign, Map<String, String> map) {
        if (StringUtils.equals(sign, map.get(ANDROID))) {
            return 1;
        } else if (StringUtils.equals(sign, map.get(IOS))) {
            return 2;
        } else if (StringUtils.equals(sign, map.get(WXMINI))) {
            return 3;
        }
        return 0;
    }
}
