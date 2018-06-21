package com.chouchongkeji.dial.dao.user;

import com.chouchongkeji.dial.pojo.user.UserPreference;

public interface UserPreferenceMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserPreference record);

    int insertSelective(UserPreference record);

    UserPreference selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserPreference record);

    int updateByPrimaryKey(UserPreference record);
}