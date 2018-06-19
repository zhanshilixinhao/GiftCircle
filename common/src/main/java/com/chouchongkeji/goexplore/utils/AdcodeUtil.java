package com.chouchongkeji.goexplore.utils;

/**
 * @author yichenshanren
 * @date 2017/12/26
 */

public class AdcodeUtil {

    /**
     * 获取行政区级别
     *
     * @param adcode 行政区编码
     * @return 1 省 2 城市 3 行政区
     */
    public static int getLevel(int adcode) {
        int level = 1;
        if (adcode % 10000 == 0) {
            level = 1;
        } else if (adcode % 100 == 0) {
            level = 2;
        } else {
            level = 3;
        }
        return level;
    }

}
