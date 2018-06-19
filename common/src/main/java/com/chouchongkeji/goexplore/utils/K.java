package com.chouchongkeji.goexplore.utils;

/**
 * 生成某个东西的key
 *
 * @author yichenshanren
 * @date 2017/11/28
 */

public class K {

    public static final String PREFIX = "gic-"; // 优咖
    public static final String SPLIT = "-"; // 分隔符

    // 会员信息
    public static final String USER_SENT_PWD = "USER_SENT_PWD";
    public static final String USER_SENT_PWD_VERIFY = "USER_SENT_PWD_VERIFY";
    // banner列表
    public static final String BANNERS = "banners";


    public static String genKey(String name, Object id) {
        return String.format("%s%s%s%s", PREFIX, name, SPLIT, String.valueOf(id));
    }

}
