package com.chouchongkeji.dao.gift.item;

import com.chouchongkeji.pojo.gift.item.ItemOrderDetail;

import java.util.List;

public interface ItemOrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemOrderDetail record);

    int insertSelective(ItemOrderDetail record);

    ItemOrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemOrderDetail record);

    int updateByPrimaryKey(ItemOrderDetail record);

    int batchInsert(List<ItemOrderDetail> list);

}