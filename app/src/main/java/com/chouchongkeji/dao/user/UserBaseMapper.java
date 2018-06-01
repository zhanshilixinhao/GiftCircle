package com.chouchongkeji.dao.user;

import com.chouchongkeji.pojo.user.UserBase;

public interface UserBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBase record);

    int insertSelective(UserBase record);

    UserBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBase record);

    int updateByPrimaryKey(UserBase record);

    UserBase selectByAccount(String username);

}