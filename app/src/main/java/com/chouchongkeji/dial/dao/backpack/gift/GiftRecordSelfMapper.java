package com.chouchongkeji.dial.dao.backpack.gift;

import com.chouchongkeji.dial.pojo.home.GiftRecordSelf;

public interface GiftRecordSelfMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftRecordSelf record);

    int insertSelective(GiftRecordSelf record);

    GiftRecordSelf selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftRecordSelf record);

    int updateByPrimaryKey(GiftRecordSelf record);
}