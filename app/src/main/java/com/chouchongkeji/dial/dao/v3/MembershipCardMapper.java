package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.MembershipCard;

public interface MembershipCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MembershipCard record);

    int insertSelective(MembershipCard record);

    MembershipCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MembershipCard record);

    int updateByPrimaryKey(MembershipCard record);
}