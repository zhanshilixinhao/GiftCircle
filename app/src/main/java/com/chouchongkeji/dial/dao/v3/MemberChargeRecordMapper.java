package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.MemberChargeRecord;
import com.chouchongkeji.service.v3.vo.ChargeDetailVo;
import com.chouchongkeji.service.v3.vo.ChargeListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberChargeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberChargeRecord record);

    int insertSelective(MemberChargeRecord record);

    MemberChargeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberChargeRecord record);

    int updateByPrimaryKey(MemberChargeRecord record);

    /**
     * 会员卡充值记录
     * @param userId
     * @param id
     * @return
     */
    List<ChargeListVo> selectByMembertCardId(@Param("userId") Integer userId, @Param("id") Integer id);

    /**
     * 充值详情
     * @param id
     * @param userId
     * @return
     */
    ChargeDetailVo selectByKeyUserId(@Param("id") Integer id, @Param("userId") Integer userId);
}
