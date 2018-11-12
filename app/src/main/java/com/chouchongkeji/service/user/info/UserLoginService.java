package com.chouchongkeji.service.user.info;

import com.chouchongkeji.goexplore.common.Response;
import com.yichen.auth.mvc.AppClient;

/**
 * @author linqin
 * @date 2018/6/5
 */
public interface UserLoginService {
    Response wxLogin( Integer client, String code);

    Response bindPhone(String phone, String openid, Integer client);
}
