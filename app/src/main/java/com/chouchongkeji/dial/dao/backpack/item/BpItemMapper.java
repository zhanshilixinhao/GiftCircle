package com.chouchongkeji.dial.dao.backpack.item;

import com.chouchongkeji.dial.pojo.backpack.Vbp;
import com.chouchongkeji.dial.pojo.backpack.item.BpItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BpItem record);

    int insertSelective(BpItem record);

    BpItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BpItem record);

    int updateByPrimaryKey(BpItem record);

    BpItem selectByUserIdAndBpItemId(@Param("userId") Integer userId,@Param("bpItemId") Integer bpItemId);


    List<Vbp> selectAllByUserId(Integer userId);
}