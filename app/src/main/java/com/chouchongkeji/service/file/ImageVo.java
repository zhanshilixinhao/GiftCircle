package com.chouchongkeji.service.file;

/**
 * 图片
 * @author yichenshanren
 * @date 2017/10/12
 */

public class ImageVo {

    private String uri;
    private String url;

    public ImageVo() {
    }

    public ImageVo(String uri, String url) {
        this.uri = uri;
        this.url = url;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}