package com.chouchongkeji.dial.dao.backpack.gift;

import com.chouchongkeji.dial.pojo.gift.virtualItem.GiftRecord;
import com.chouchongkeji.service.backpack.gift.vo.GiftTaskVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GiftRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftRecord record);

    int insertSelective(GiftRecord record);

    GiftRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftRecord record);

    int updateByPrimaryKey(GiftRecord record);

    List<GiftTaskVo> selectAllByTargetTime();

    int updateStatusById(@Param("recordId") Integer recordId, @Param("status") Byte status);

    /**
     * 根据用户id查询用户的总的收入和汁出信息
     *
     * @param userId
     * @return
     */
    Map selectInExByUserId(@Param("userId") Integer userId);
}