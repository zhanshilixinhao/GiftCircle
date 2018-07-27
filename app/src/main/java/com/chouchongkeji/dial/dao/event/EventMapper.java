package com.chouchongkeji.dial.dao.event;

import com.chouchongkeji.dial.pojo.event.Event;

import java.util.List;

public interface EventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKey(Event record);

    List<Event> selectAll(Integer userId);


}