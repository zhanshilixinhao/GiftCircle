package com.chouchongkeji.dial.dao.gift.item;

import com.chouchongkeji.dial.pojo.gift.item.ItemOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemOrder record);

    int insertSelective(ItemOrder record);

    ItemOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemOrder record);

    int updateByPrimaryKey(ItemOrder record);

    ItemOrder selectByUserIdOrderNo(@Param("userId") Integer userId,@Param("orderNo") Long orderNo);

    List<ItemOrder> selectByUserId(Integer userId);

}