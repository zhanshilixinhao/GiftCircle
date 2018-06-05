package com.yichen.auth.properties;

/**
 * @author yichenshanren
 * @date 2018/6/5
 */

public class SignProperties {

    private boolean needSign = true;

    private String[] urls;

    public boolean isNeedSign() {
        return needSign;
    }

    public void setNeedSign(boolean needSign) {
        this.needSign = needSign;
    }

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }
}
