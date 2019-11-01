package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.UserMemberCard;
import com.chouchongkeji.service.v3.vo.CardVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashSet;
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
    List<CardVo> selectByUserId(Integer userId, @Param("keywords") String keywords);

    HashSet<Integer> selectCardIdsByUserId(Integer userId);

    /**
     * 会员卡详情
     * @param id
     * @return
     */
    CardVo selectByKey(Integer id);

    /**
     * 根据用户id和会员卡id查询信息
     * @param userId
     * @param cardId
     * @return
     */
    UserMemberCard selectByCardIdUserId(@Param("userId") Integer userId, @Param("cardId") Integer cardId);
}
