package com.chouchongkeji.dial.dao.backpack.consignment;

import com.chouchongkeji.dial.pojo.backpack.consignment.Consignment;
import com.chouchongkeji.service.backpack.consignment.vo.PriceVo;
import org.apache.ibatis.annotations.Param;

public interface ConsignmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Consignment record);

    int insertSelective(Consignment record);

    Consignment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Consignment record);

    int updateByPrimaryKey(Consignment record);

    PriceVo selectByTargetIdType(@Param("targetId") int targetId, @Param("type") int type);
}