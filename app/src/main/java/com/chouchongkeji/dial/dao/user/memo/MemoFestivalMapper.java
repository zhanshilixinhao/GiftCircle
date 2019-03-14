package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoFestival;
import com.chouchongkeji.service.user.memo.vo.MemoItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemoFestivalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemoFestival record);

    int insertSelective(MemoFestival record);

    MemoFestival selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemoFestival record);

    int updateByPrimaryKey(MemoFestival record);

    /**
     * 查询所有
     * @return
     */
    List<MemoItemVo> selectAll();

    /**
     * 查询节日详情
     * @param id
     * @return
     */
    MemoFestival selectById(Integer id);

    /**
     * 7天内的节日
     * @return
     */
    List<MemoItemVo> selectHomeFestival(@Param("start") Long start, @Param("end") Long end);
}
