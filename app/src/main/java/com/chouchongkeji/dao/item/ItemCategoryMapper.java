package com.chouchongkeji.dao.item;

import com.chouchongkeji.pojo.item.ItemCategory;

public interface ItemCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemCategory record);

    int insertSelective(ItemCategory record);

    ItemCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemCategory record);

    int updateByPrimaryKey(ItemCategory record);
}