package com.chouchongkeji.dial.dao.user;

import com.chouchongkeji.dial.pojo.user.AppUser;

public interface AppUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppUser record);

    int insertSelective(AppUser record);

    AppUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppUser record);

    int updateByPrimaryKey(AppUser record);

    AppUser selectByPhone(String phone);

    AppUser selectByUserId(Integer userId);
}