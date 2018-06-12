package com.chouchongkeji.dao.gift.item;

import com.chouchongkeji.pojo.gift.item.ItemCategory;

import java.util.List;

public interface ItemCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemCategory record);

    int insertSelective(ItemCategory record);

    ItemCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemCategory record);

    int updateByPrimaryKey(ItemCategory record);

    List<ItemCategory> selectAll();

}