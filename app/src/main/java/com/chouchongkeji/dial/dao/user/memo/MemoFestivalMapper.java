package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoFestival;

public interface MemoFestivalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemoFestival record);

    int insertSelective(MemoFestival record);

    MemoFestival selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemoFestival record);

    int updateByPrimaryKey(MemoFestival record);
}