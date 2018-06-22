package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoActivity;

public interface MemoActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemoActivity record);

    int insertSelective(MemoActivity record);

    MemoActivity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemoActivity record);

    int updateByPrimaryKey(MemoActivity record);
}