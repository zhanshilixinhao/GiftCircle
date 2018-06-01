package com.chouchongkeji.goexplore.pay.weixin.service;

/**
 * @author yichenshanren on 2016/7/21.
 */

public class NameValueModel {

    private String name;

    public NameValueModel() {
    }

    public NameValueModel(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
