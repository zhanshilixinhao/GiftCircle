package com.yichen.auth.service;

import com.yichen.auth.authentication.MyToken;

/**
 * 保存用户新到缓存
 *
 * @author yichenshanren
 * @date 2017/12/3
 */

public interface UserDtailsCacheService {

    void updateCache(MyToken token, String username);

}
