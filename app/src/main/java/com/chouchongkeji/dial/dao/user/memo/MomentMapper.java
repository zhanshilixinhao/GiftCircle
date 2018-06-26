package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.Moment;
import com.chouchongkeji.service.user.friend.vo.MomentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MomentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Moment record);

    int insertSelective(Moment record);

    Moment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Moment record);

    int updateByPrimaryKey(Moment record);

    List<MomentVo> selectAll(@Param("userId") Integer userId);

    MomentVo selectDetailById(@Param("userId") Integer userId,
                              @Param("momentId") Integer momentId);

    List<MomentVo> selectAllByTargetUser(@Param("userId") Integer userId,
                                         @Param("targetUserId") Integer targetUserId);
}