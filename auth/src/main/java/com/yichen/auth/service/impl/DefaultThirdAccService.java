package com.yichen.auth.service.impl;

import com.chouchongkeji.goexplore.common.Response;
import com.yichen.auth.model.ThirdAccDetail;
import com.yichen.auth.service.ThirdAccService;

/**
 * 第三方账号注册登录操作
 *
 * @author yichenshanren
 * @date 2017/11/30
 */

public class DefaultThirdAccService implements ThirdAccService {

    @Override
    public ThirdAccDetail getOpenAccDetail(String openId, int type) {
        return null;
    }

    @Override
    public Response addOpenAccDetail(String openId, int type, String userName) {
        return null;
    }
}
