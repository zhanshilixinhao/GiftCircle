package com.chouchongkeji.dial.dao.backpack.consignment;

import com.chouchongkeji.dial.pojo.backpack.consignment.ConsignmentOrder;

public interface ConsignmentOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConsignmentOrder record);

    int insertSelective(ConsignmentOrder record);

    ConsignmentOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConsignmentOrder record);

    int updateByPrimaryKey(ConsignmentOrder record);
}