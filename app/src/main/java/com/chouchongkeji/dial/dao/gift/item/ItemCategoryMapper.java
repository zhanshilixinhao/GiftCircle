package com.chouchongkeji.dial.dao.gift.item;

import com.chouchongkeji.dial.pojo.gift.item.ItemCategory;
import com.chouchongkeji.service.mall.item.vo.ItemCategoryVo;

import java.util.List;

public interface ItemCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemCategory record);

    int insertSelective(ItemCategory record);

    ItemCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemCategory record);

    int updateByPrimaryKey(ItemCategory record);

    List<ItemCategoryVo> selectAll();

    /**
     * 根据父级id查询
     * @param pid
     * @return
     */
    List<ItemCategory> selectByParentId(Integer pid);

    /**
     * 一级分类列表
     * @return
     */
    List<ItemCategory> selectStailAll();
}
