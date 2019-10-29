package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.MemberChargeOrder;

public interface MemberChargeOrderMapper {
    int deleteByPrimaryKey(Long orderNo);

    int insert(MemberChargeOrder record);

    int insertSelective(MemberChargeOrder record);

    MemberChargeOrder selectByPrimaryKey(Long orderNo);

    int updateByPrimaryKeySelective(MemberChargeOrder record);

    int updateByPrimaryKey(MemberChargeOrder record);
}