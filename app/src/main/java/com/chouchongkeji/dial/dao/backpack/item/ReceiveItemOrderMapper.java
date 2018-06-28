package com.chouchongkeji.dial.dao.backpack.item;

import com.chouchongkeji.dial.pojo.backpack.item.ReceiveItemOrder;

public interface ReceiveItemOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReceiveItemOrder record);

    int insertSelective(ReceiveItemOrder record);

    ReceiveItemOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReceiveItemOrder record);

    int updateByPrimaryKey(ReceiveItemOrder record);
}