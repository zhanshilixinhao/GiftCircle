package com.chouchongkeji.dial.dao.backpack.gift;

import com.chouchongkeji.dial.pojo.gift.virtualItem.GiftRecord;
import com.chouchongkeji.service.backpack.gift.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param starting
     * @param ending
     * @param obType
     * @return
     */
    GiftInoutVo selectInExByUserId(@Param("userId") Integer userId, @Param("starting") Long starting,
                                   @Param("ending") Long ending,
                                   @Param("obType") String obType);

    List<GiftRecordItemVo> selectRecordByUserId(
            @Param("userId") Integer userId,
            @Param("starting") Long starting,
            @Param("ending") Long ending,
            @Param("obType") String obType);

    List<GiftFriendVo> selectByFriendUserId(@Param("userId") Integer userId,
                                            @Param("friendUserId") Integer friendUserId);

    /**
     * 查询赠送记录和收礼记录
     * @param userId
     * @param flag
     * @return
     */
    List<GiftSendListVo> selectListByFlagUserId(@Param("userId") Integer userId,@Param("flag") Byte flag);
}
