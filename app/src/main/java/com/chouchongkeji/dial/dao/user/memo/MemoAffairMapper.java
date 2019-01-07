package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoAffair;

public interface MemoAffairMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemoAffair record);

    int insertSelective(MemoAffair record);

    MemoAffair selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemoAffair record);

    int updateByPrimaryKey(MemoAffair record);
}