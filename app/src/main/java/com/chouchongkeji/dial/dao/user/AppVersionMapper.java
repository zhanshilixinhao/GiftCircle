package com.chouchongkeji.dial.dao.user;

import com.chouchongkeji.dial.pojo.user.AppVersion;

public interface AppVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);

    AppVersion selectByVersionCode(Integer versionCode);
}
