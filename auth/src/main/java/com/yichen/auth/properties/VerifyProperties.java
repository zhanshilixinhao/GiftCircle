package com.yichen.auth.properties;

/**
 * @author yichenshanren
 * @date 2017/11/26
 */

public class VerifyProperties {

    private int length = 6; // 6为

    private int expire = 180; // 180s

    private SmsUrlProperties[] urls;

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    public SmsUrlProperties[] getUrls() {
        return urls;
    }

    public void setUrls(SmsUrlProperties[] urls) {
        this.urls = urls;
    }
}
