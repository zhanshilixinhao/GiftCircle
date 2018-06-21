package com.chouchongkeji.dial.dao.gift.item;

import com.chouchongkeji.dial.pojo.gift.item.ItemOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemOrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemOrderDetail record);

    int insertSelective(ItemOrderDetail record);

    ItemOrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemOrderDetail record);

    int updateByPrimaryKey(ItemOrderDetail record);

    int batchInsert(List<ItemOrderDetail> list);

    List<ItemOrderDetail> selectByUserIdAndOrderNo(@Param("userId") Integer userId,@Param("orderNo") Long orderNo);
}