package com.yichen.auth.jackson;

import com.chouchongkeji.goexplore.common.DataHandler;

/**
 * @author yichenshanren
 * @date 2018/6/26
 */
public class MyDataHandler implements DataHandler {

    @Override
    public void handle(Object data) {
        ImgUrl imgUrl = data.getClass().getAnnotation(ImgUrl.class);
        if (imgUrl == null) {
            return;
        }

    }
}
