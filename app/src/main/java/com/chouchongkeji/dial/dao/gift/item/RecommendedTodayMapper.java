package com.chouchongkeji.dial.dao.gift.item;

import com.chouchongkeji.dial.pojo.gift.item.RecommendedToday;
import com.chouchongkeji.service.mall.item.vo.ItemListVo2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendedTodayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecommendedToday record);

    int insertSelective(RecommendedToday record);

    RecommendedToday selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecommendedToday record);

    int updateByPrimaryKey(RecommendedToday record);

    List<ItemListVo2> selectByTime(@Param("time") Long time,@Param("endTime") Long endTime);
}
