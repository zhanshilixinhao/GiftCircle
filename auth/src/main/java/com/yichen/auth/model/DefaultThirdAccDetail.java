package com.yichen.auth.model;

/**
 * 默认的第三方账号信息
 *
 * @author yichenshanren
 * @date 2017/11/30
 */

public class DefaultThirdAccDetail implements ThirdAccDetail {

    private String username;
    private String opneId;
    private int accType;

    public DefaultThirdAccDetail() {
    }

    public DefaultThirdAccDetail(String username, String opneId, int accType) {
        this.username = username;
        this.opneId = opneId;
        this.accType = accType;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getOepnId() {
        return opneId;
    }

    @Override
    public int getAccType() {
        return accType;
    }
}
