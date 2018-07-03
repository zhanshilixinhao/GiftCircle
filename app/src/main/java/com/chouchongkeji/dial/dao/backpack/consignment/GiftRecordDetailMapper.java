package com.chouchongkeji.dial.dao.backpack.consignment;

import com.chouchongkeji.dial.pojo.gift.virtualItem.GiftRecordDetail;

import java.util.List;

public interface GiftRecordDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftRecordDetail record);

    int insertSelective(GiftRecordDetail record);

    GiftRecordDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftRecordDetail record);

    int updateByPrimaryKey(GiftRecordDetail record);

    int insertBatch(List<GiftRecordDetail> list);
}