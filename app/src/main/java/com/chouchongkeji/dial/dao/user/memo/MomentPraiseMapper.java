package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MomentPraise;
import org.apache.ibatis.annotations.Param;

public interface MomentPraiseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MomentPraise record);

    int insertSelective(MomentPraise record);

    MomentPraise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MomentPraise record);

    int updateByPrimaryKey(MomentPraise record);

    MomentPraise selectByMomentIdAndUserId(@Param("momentId") Integer momentId,
                                           @Param("userId") Integer userId);
}