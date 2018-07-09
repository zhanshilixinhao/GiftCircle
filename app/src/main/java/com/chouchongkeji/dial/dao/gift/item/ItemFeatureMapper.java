package com.chouchongkeji.dial.dao.gift.item;

import com.chouchongkeji.dial.pojo.gift.item.ItemFeature;
import com.chouchongkeji.service.mall.item.vo.ItemFeatureVo;

import java.util.List;

public interface ItemFeatureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemFeature record);

    int insertSelective(ItemFeature record);

    ItemFeature selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemFeature record);

    int updateByPrimaryKey(ItemFeature record);

    List<ItemFeatureVo> selectFeatureByItemId(Integer itemId);

}