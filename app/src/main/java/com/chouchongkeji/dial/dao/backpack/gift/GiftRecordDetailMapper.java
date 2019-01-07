package com.chouchongkeji.dial.dao.backpack.gift;

import com.chouchongkeji.dial.pojo.backpack.gift.GiftRecordDetail;
import com.chouchongkeji.service.backpack.gift.vo.GiftRecordDetailVo;
import com.chouchongkeji.service.backpack.gift.vo.RecordDetailVo;
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

    /**
     * 根据用户id和好友id查询赠送次数
     * @param userId
     * @param friendUserId
     * @return
     */
    int selectCount(@Param("userId") Integer userId,@Param("friendUserId") Integer friendUserId);

    /**
     * 查询用户最新赠送记录
     * @return
     */
    List<RecordDetailVo> selectOne();

    /**
     * 根据礼物记录id查询记录详情
     * @param recordId
     * @return
     */
    List<GiftRecordDetailVo> selectDetailByRecordId(Integer recordId);

    /**
     * 删除记录详情
     * @param id
     * @return
     */
    int updateStatusByRecordId(Integer id);
}
