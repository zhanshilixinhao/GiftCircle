package com.chouchongkeji.dao.gift.item;

import com.chouchongkeji.pojo.gift.item.ItemComment;
import com.chouchongkeji.service.gift.item.vo.ItemCommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemComment record);

    int insertSelective(ItemComment record);

    ItemComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemComment record);

    int updateByPrimaryKey(ItemComment record);

    List<ItemCommentVo> selectByItemId(@Param("id") Integer id);
}