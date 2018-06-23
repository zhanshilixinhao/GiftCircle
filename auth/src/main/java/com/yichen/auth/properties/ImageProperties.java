package com.yichen.auth.properties;

/**
 * @author yichenshanren
 * @date 2018/6/23
 */

public class ImageProperties {

    private String host = "http://localhost:8080/";

    private boolean needSplice = false;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isNeedSplice() {
        return needSplice;
    }

    public void setNeedSplice(boolean needSplice) {
        this.needSplice = needSplice;
    }
}
