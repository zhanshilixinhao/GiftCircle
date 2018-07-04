package com.chouchongkeji.dial.dao.backpack.gift;

public interface GiftRecordDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftRecordDetail record);

    int insertSelective(GiftRecordDetail record);

    GiftRecordDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftRecordDetail record);

    int updateByPrimaryKey(GiftRecordDetail record);
}