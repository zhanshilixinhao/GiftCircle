package com.chouchongkeji.dial.dao.user;

import com.chouchongkeji.dial.pojo.user.GiftPreferenceDict;

public interface GiftPreferenceDictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftPreferenceDict record);

    int insertSelective(GiftPreferenceDict record);

    GiftPreferenceDict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftPreferenceDict record);

    int updateByPrimaryKey(GiftPreferenceDict record);
}