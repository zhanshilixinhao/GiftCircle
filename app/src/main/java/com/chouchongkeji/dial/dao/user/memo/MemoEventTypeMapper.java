package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoEventType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemoEventTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemoEventType record);

    int insertSelective(MemoEventType record);

    MemoEventType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemoEventType record);

    int updateByPrimaryKey(MemoEventType record);

    /**
     * 根据用户id和名称查询事件类型
     * @param userId
     * @param name
     * @return
     */
    MemoEventType selectByUserIdAndName(@Param("userId") Integer userId,@Param("name") String name);

    /**
     * 根据用户id查询列表
     * @param userId
     * @return
     */
    List<MemoEventType> selectByUserId(Integer userId);
}
