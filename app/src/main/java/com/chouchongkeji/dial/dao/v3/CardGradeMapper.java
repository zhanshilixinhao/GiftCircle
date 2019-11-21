package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.CardGrade;

public interface CardGradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CardGrade record);

    int insertSelective(CardGrade record);

    CardGrade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CardGrade record);

    int updateByPrimaryKey(CardGrade record);
}