package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemonEvent;
import com.chouchongkeji.service.user.memo.vo.MemoItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemonEventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemonEvent record);

    int insertSelective(MemonEvent record);

    MemonEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemonEvent record);

    int updateByPrimaryKey(MemonEvent record);

    int deleteByPrimaryKeyAndUserId(@Param("id") Integer id,
                                    @Param("userId") Integer userId);

    List<MemoItemVo> selectByUserIdAndDate(@Param("userId") Integer userId,
                                           @Param("start") Long start,
                                           @Param("end") Long end);
}