package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.Moment;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.user.friend.vo.CountVo;
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

    List<MomentVo> selectAll(@Param("userId") Integer userId, @Param("page") PageQuery page);

    MomentVo selectDetailById(@Param("userId") Integer userId,
                              @Param("momentId") Integer momentId);

    List<MomentVo> selectAllByTargetUser(@Param("userId") Integer userId,
                                         @Param("targetUserId") Integer targetUserId,
                                         @Param("page") PageQuery page
    );

    List<Moment> selectRecentByUserId(@Param("userId") Integer userId);

    List<MomentVo> selectAllAny(
            @Param("userId") Integer userId,
            @Param("page") PageQuery page);

    /**\
     * 获取未查看的点赞数量
     * @param userId
     * @param time
     * @return
     */
    CountVo selectByUserIdAndCreated(@Param("userId") Integer userId, @Param("time") Long time);
}
