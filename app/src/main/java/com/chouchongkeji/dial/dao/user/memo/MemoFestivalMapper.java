package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoFestival;
import com.chouchongkeji.service.user.memo.vo.MemoItemVo;

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
}
