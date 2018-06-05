package com.chouchongkeji.service.user;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author linqin
 * @date 2018/6/5
 */
public interface UserService {
    /**
     * 获取用户详细信息
     * @author linqin
     * @date 2018/6/5
     */
    Response getProfile(Integer memberId);
}
