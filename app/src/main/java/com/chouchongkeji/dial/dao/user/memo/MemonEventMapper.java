package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemonEvent;

public interface MemonEventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemonEvent record);

    int insertSelective(MemonEvent record);

    MemonEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemonEvent record);

    int updateByPrimaryKey(MemonEvent record);
}