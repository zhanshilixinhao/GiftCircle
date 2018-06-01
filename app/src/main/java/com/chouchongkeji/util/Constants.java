package com.chouchongkeji.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author linqin
 * @date 2018/5/21
 */
public class Constants {
    public static final String DEFALUT_AVATAR = "avatar.jpg";

    public static final String getRandomName(){
        return RandomStringUtils.randomNumeric(7);
    }
}
