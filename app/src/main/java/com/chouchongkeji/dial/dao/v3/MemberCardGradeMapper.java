package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.MemberCardGrade;

public interface MemberCardGradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberCardGrade record);

    int insertSelective(MemberCardGrade record);

    MemberCardGrade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberCardGrade record);

    int updateByPrimaryKey(MemberCardGrade record);
}