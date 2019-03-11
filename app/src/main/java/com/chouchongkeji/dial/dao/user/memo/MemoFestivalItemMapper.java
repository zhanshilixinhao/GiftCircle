package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoFestivalItem;

public interface MemoFestivalItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemoFestivalItem record);

    int insertSelective(MemoFestivalItem record);

    MemoFestivalItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemoFestivalItem record);

    int updateByPrimaryKey(MemoFestivalItem record);
}