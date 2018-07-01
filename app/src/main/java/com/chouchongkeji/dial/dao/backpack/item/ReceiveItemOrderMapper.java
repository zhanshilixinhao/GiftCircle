package com.chouchongkeji.dial.dao.backpack.item;

import com.chouchongkeji.dial.pojo.backpack.item.ReceiveItemOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReceiveItemOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReceiveItemOrder record);

    int insertSelective(ReceiveItemOrder record);

    ReceiveItemOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReceiveItemOrder record);

    int updateByPrimaryKey(ReceiveItemOrder record);

    List<ReceiveItemOrder>  selectByUserIdStatus(@Param("userId") Integer userId, @Param("status") Integer status);

    ReceiveItemOrder selectByUserIdOrderNo(@Param("userId") Integer userId,@Param("orderNo") Long orderNo);

}