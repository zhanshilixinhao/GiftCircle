package com.chouchongkeji.service.user.friend.vo;


import com.yichen.auth.jackson.ImgUrl;

/**
 * @author yichenshanren
 * @date 2018/6/26
 */

public class MediaVo {

    private Integer type;

    @ImgUrl
    private String url;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
