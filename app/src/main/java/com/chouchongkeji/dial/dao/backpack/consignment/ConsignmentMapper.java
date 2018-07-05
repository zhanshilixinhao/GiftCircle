package com.chouchongkeji.dial.dao.backpack.consignment;

import com.chouchongkeji.dial.pojo.backpack.consignment.Consignment;
import com.chouchongkeji.service.backpack.consignment.vo.ConListVo;
import com.chouchongkeji.service.backpack.consignment.vo.ConsignmentVo;
import com.chouchongkeji.service.backpack.consignment.vo.PriceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsignmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Consignment record);

    int insertSelective(Consignment record);

    Consignment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Consignment record);

    int updateByPrimaryKey(Consignment record);

    PriceVo selectByTargetIdType(@Param("targetId") int targetId, @Param("type") int type);

    List<ConsignmentVo> selectListByTargetIdType(@Param("targetId") int targetId, @Param("type") int type);

    List<ConListVo> selectAllItem();

    List<Consignment> selectList(Integer userId, Byte user, Byte condition);

}