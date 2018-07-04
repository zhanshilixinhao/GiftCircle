package com.chouchongkeji.dial.dao.backpack.gift;

import com.chouchongkeji.dial.pojo.gift.virtualItem.AppMessage;

public interface AppMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppMessage record);

    int insertSelective(AppMessage record);

    AppMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppMessage record);

    int updateByPrimaryKey(AppMessage record);
}