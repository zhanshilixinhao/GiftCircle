package com.chouchongkeji.goexplore.utils;

/**
 * 生成某个东西的key
 *
 * @author yichenshanren
 * @date 2017/11/28
 */

public class KeyGenUtils {

    public static final String PREFIX = "3-"; // 优咖
    public static final String SPLIT = "-"; // 分隔符

    // 会员信息
    public static final String MEMBER_PORFILE = "mempro";
    // banner列表
    public static final String BANNERS = "banners";


    public static String genKey(String name, Object id) {
        return String.format("%s%s%s%s", PREFIX, name, SPLIT, String.valueOf(id));
    }

}
