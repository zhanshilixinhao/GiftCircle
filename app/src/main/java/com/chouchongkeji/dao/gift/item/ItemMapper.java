package com.chouchongkeji.dao.gift.item;

import com.chouchongkeji.pojo.gift.item.Item;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    List<Item> selectAll(@Param("classes") Integer classes,
                         @Param("gender") Integer gender,
                         @Param("minAge") Integer minAge,
                         @Param("maxAge") Integer maxAge,
                         @Param("minPrice") BigDecimal minPrice,
                         @Param("maxPrice") BigDecimal maxPrice,
                         @Param("eventId") Integer eventId);
}