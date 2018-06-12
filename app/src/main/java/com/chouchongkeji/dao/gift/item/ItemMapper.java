package com.chouchongkeji.dao.gift.item;

import com.chouchongkeji.pojo.gift.item.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    List<Item> selectAll(Integer classes, Integer gender, Integer minAge, Integer maxAge,
                         BigDecimal minPrice, BigDecimal maxPrice, Integer eventId);
}