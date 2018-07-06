package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoActivity;
import com.chouchongkeji.service.user.memo.vo.HomeMemoItemVo;
import com.chouchongkeji.service.user.memo.vo.MemoItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemoActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemoActivity record);

    int insertSelective(MemoActivity record);

    MemoActivity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemoActivity record);

    int updateByPrimaryKey(MemoActivity record);

    List<MemoItemVo> selectByUserIdAndDate(@Param("userId") Integer userId,
                                           @Param("start") Long start,
                                           @Param("end") Long end);

    int deleteByPrimaryKeyAndUserId(@Param("id") Integer id,
                                    @Param("userId") Integer userId);

    List<HomeMemoItemVo> selectLastByUserId(@Param("userId") Integer userId);
}