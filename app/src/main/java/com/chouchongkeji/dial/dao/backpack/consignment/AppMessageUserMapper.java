package com.chouchongkeji.dial.dao.backpack.consignment;

import com.chouchongkeji.dial.pojo.gift.virtualItem.AppMessageUser;

public interface AppMessageUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppMessageUser record);

    int insertSelective(AppMessageUser record);

    AppMessageUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppMessageUser record);

    int updateByPrimaryKey(AppMessageUser record);
}