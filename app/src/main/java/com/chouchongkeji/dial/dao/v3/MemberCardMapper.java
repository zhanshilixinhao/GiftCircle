package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.MemberCard;
import com.chouchongkeji.service.v3.vo.EventVo;

import java.util.List;

public interface MemberCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberCard record);

    int insertSelective(MemberCard record);

    MemberCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberCard record);

    int updateByPrimaryKey(MemberCard record);

    /**
     * 礼遇圈会员卡充值规则
     * @return
     */
    List<EventVo> selectAll();

}
