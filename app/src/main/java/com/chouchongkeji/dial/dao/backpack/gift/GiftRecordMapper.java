package com.chouchongkeji.dial.dao.backpack.gift;

import com.chouchongkeji.dial.pojo.gift.virtualItem.GiftRecord;

public interface GiftRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftRecord record);

    int insertSelective(GiftRecord record);

    GiftRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftRecord record);

    int updateByPrimaryKey(GiftRecord record);
}