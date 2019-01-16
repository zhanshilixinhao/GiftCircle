package com.chouchongkeji.dial.dao.home;

import com.chouchongkeji.dial.pojo.home.Festival;

import java.util.List;

public interface FestivalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Festival record);

    int insertSelective(Festival record);

    Festival selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Festival record);

    int updateByPrimaryKey(Festival record);

    List<Festival> selectByAll();
}
