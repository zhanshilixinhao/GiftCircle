package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.UserMemberCard;
import com.chouchongkeji.service.v3.vo.CardVo;

import java.util.List;

public interface UserMemberCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMemberCard record);

    int insertSelective(UserMemberCard record);

    UserMemberCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMemberCard record);

    int updateByPrimaryKey(UserMemberCard record);

    /**
     * 查询用户的卡包
     * @param userId
     * @return
     */
    List<CardVo> selectByUserId(Integer userId);
}
