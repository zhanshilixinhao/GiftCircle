package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.MemberEvent;

public interface MemberEventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberEvent record);

    int insertSelective(MemberEvent record);

    MemberEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberEvent record);

    int updateByPrimaryKey(MemberEvent record);
}