package com.chouchongkeji.dial.dao.user;

import com.chouchongkeji.dial.pojo.user.UserTagDict;

import java.util.List;

public interface UserTagDictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTagDict record);

    int insertSelective(UserTagDict record);

    UserTagDict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTagDict record);

    int updateByPrimaryKey(UserTagDict record);

    List<UserTagDict> selectAll();

}