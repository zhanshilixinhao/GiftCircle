package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.StoreMemberEvent;

public interface StoreMemberEventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreMemberEvent record);

    int insertSelective(StoreMemberEvent record);

    StoreMemberEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreMemberEvent record);

    int updateByPrimaryKey(StoreMemberEvent record);
}