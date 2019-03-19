package com.chouchongkeji.dial.dao.home;

import com.chouchongkeji.dial.pojo.home.Almanac;

public interface AlmanacMapper {
    int deleteByPrimaryKey(String day);

    int insert(Almanac record);

    int insertSelective(Almanac record);

    Almanac selectByPrimaryKey(String day);

    int updateByPrimaryKeySelective(Almanac record);

    int updateByPrimaryKey(Almanac record);
}