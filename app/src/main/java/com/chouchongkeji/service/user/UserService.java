package com.chouchongkeji.service.user;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.pojo.user.AppUser;

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


    /**
     * 修改用户信息
     *
     * @param appUser
     * @return
     * @author linqin
     * @date 2018/6/5
     */
    Response updateProfile(Integer userId, AppUser appUser);
}
