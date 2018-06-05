package com.chouchongkeji.service.user.impl;

import com.chouchongkeji.dao.user.AppUserMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.pojo.user.AppUser;
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linqin
 * @date 2018/6/5
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private ServiceProperties serviceProperties;

    /**
     * 获取用户详细信息
     * @param userId 用户id
     * @return
     * @author linqin
     * @date 2018/6/5
     */
    @Override
    public Response getProfile(Integer userId) {
        //根据用户信息获取用户详细信息
        AppUser appUser = appUserMapper.selectByUserId(userId);
        //判断用户信息是否存在
        if (appUser == null){
            return ResponseFactory.err("用户信息不存在");
        }
        // 头像要加上服务器地址
        appUser.setPassword(null);
        appUser.setAvatar(serviceProperties.getImgHost() + appUser.getAvatar());
        return ResponseFactory.sucData(appUser);

    }




}
