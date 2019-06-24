package com.chouchongkeji.dial.dao.gift.item;

import com.chouchongkeji.dial.pojo.gift.item.RecommendedToday;

public interface RecommendedTodayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecommendedToday record);

    int insertSelective(RecommendedToday record);

    RecommendedToday selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecommendedToday record);

    int updateByPrimaryKey(RecommendedToday record);
}