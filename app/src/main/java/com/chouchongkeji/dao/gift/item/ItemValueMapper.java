package com.chouchongkeji.dao.gift.item;

import com.chouchongkeji.pojo.gift.item.ItemValue;

public interface ItemValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemValue record);

    int insertSelective(ItemValue record);

    ItemValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemValue record);

    int updateByPrimaryKey(ItemValue record);

}