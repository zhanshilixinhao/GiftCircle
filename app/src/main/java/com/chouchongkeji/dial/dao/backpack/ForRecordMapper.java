package com.chouchongkeji.dial.dao.backpack;

import com.chouchongkeji.dial.pojo.backpack.ForRecord;
import com.chouchongkeji.service.backpack.base.vo.ForRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashSet;
import java.util.List;

public interface ForRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ForRecord record);

    int insertSelective(ForRecord record);

    ForRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ForRecord record);

    int updateByPrimaryKey(ForRecord record);

    List<ForRecordVo> selectAllByUserId(@Param("userId")Integer userId,@Param("type") Integer type);

    ForRecord selectByUserIdAndForRecordId(@Param("userId") Integer userId,@Param("forRecordId") Integer forRecordId);




    /**
     * 单边删除索要记录
     * @param rId
     * @return
     */
    int updateStatusByRId(Integer rId);

    int updateDelByRId(Integer rId);
}
