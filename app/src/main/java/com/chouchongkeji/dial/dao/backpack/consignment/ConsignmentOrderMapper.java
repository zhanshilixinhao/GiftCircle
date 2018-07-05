package com.chouchongkeji.dial.dao.backpack.consignment;

import com.chouchongkeji.dial.pojo.backpack.consignment.ConsignmentOrder;
import org.apache.ibatis.annotations.Param;

public interface ConsignmentOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConsignmentOrder record);

    int insertSelective(ConsignmentOrder record);

    ConsignmentOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConsignmentOrder record);

    int updateByPrimaryKey(ConsignmentOrder record);

    ConsignmentOrder selectByUserIdOrder(@Param("userId") Integer userId,@Param("orderNo") Long orderNo);

    String selectBySellUserId(Integer sellUserId);

    ConsignmentOrder selectByOrderNo(Long orderNo);

}