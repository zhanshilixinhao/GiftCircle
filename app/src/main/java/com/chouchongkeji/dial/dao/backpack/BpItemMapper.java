package com.chouchongkeji.dial.dao.backpack;

import com.chouchongkeji.dial.pojo.backpack.BpItem;
import com.chouchongkeji.dial.pojo.backpack.Vbp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BpItem record);

    int insertSelective(BpItem record);

    BpItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BpItem record);

    int updateByPrimaryKey(BpItem record);


    BpItem selectByUserIdAndBpItemId(@Param("userId") Integer userId, @Param("bpId") Long bpId);

    List<Vbp> selectAllByUserId(@Param("userId") Integer userId,
                                @Param("type") Integer type);

    int insertBatch(List<BpItem> list);
}