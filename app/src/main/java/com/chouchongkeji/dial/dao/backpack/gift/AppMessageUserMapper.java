package com.chouchongkeji.dial.dao.backpack.gift;

import com.chouchongkeji.dial.pojo.gift.virtualItem.AppMessageUser;

import java.util.List;

public interface AppMessageUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppMessageUser record);

    int insertSelective(AppMessageUser record);

    AppMessageUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppMessageUser record);

    int updateByPrimaryKey(AppMessageUser record);

    int insertBatch(List<AppMessageUser> list);
}