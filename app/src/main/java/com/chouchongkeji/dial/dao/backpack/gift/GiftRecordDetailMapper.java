package com.chouchongkeji.dial.dao.backpack.gift;

import com.chouchongkeji.dial.pojo.backpack.gift.GiftRecordDetail;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GiftRecordDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftRecordDetail record);

    int insertSelective(GiftRecordDetail record);

    GiftRecordDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftRecordDetail record);

    int updateByPrimaryKey(GiftRecordDetail record);

    int updateStatusById(@Param("id") Integer id,
                         @Param("status") Byte status);

    List<GiftRecordDetail> selectByDate(@Param("userId") Integer userId,
                                        @Param("started") Date started);

    int updateUserIdAndStatusByRecordId(@Param("giftRecordId") Integer giftRecordId,
                                        @Param("userId") Integer userId);

    List<GiftRecordDetail> selectByRecordId(@Param("recordId") Integer id);

    /**
     * 根据记录详情id查询送礼者id
     * @param recordDetailId
     * @return
     */
    Integer selectByRecordDetailId(Integer recordDetailId);
}
