package com.chouchongkeji.dial.dao.gift.item;

import com.chouchongkeji.dial.pojo.gift.item.OrderCollect;

public interface OrderCollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderCollect record);

    int insertSelective(OrderCollect record);

    OrderCollect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderCollect record);

    int updateByPrimaryKey(OrderCollect record);
}