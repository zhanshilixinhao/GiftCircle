package com.chouchongkeji.dial.dao.backpack.item;

import com.chouchongkeji.dial.pojo.backpack.item.Discounting;

public interface DiscountingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Discounting record);

    int insertSelective(Discounting record);

    Discounting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Discounting record);

    int updateByPrimaryKey(Discounting record);
}