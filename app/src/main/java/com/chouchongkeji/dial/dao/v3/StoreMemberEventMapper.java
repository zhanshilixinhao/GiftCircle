package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.StoreMemberEvent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreMemberEventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreMemberEvent record);

    int insertSelective(StoreMemberEvent record);

    StoreMemberEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreMemberEvent record);

    int updateByPrimaryKey(StoreMemberEvent record);

    StoreMemberEvent selectByUserIdCardIdOrderNo(@Param("userId") Integer userId, @Param("membershipCardId") Integer membershipCardId, @Param("orderNo") Long orderNo);

    List<StoreMemberEvent> selectByUserIdCardId(@Param("userId") Integer userId, @Param("membershipCardId") Integer membershipCardId);

    List<StoreMemberEvent> selectByUserIdCardId1(@Param("userId") Integer userId, @Param("membershipCardId") Integer membershipCardId);

}
