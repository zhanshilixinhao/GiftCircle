package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.StoreMemberCharge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreMemberChargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreMemberCharge record);

    int insertSelective(StoreMemberCharge record);

    StoreMemberCharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreMemberCharge record);

    int updateByPrimaryKey(StoreMemberCharge record);

    List<StoreMemberCharge> selectByUserIdCardId(@Param("cardId") Integer cardId, @Param("userId") Integer userId);
}
