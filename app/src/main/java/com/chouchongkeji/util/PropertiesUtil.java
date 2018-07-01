package com.chouchongkeji.util;

/**
 * @author yichenshanren
 * @date 2018/6/20
 */

public class PropertiesUtil {

    private static String IMG_HOST = "";

    public static void setImgHost(String imgHost) {
        IMG_HOST = imgHost;
    }


    public static String getImageHost() {
        return IMG_HOST;
    }

    public static String imgSavePath() {
        return "/usr/local/tomcat/webapps/image/";
    }
}
