package com.chouchongkeji.dao.gift.item;

import com.chouchongkeji.pojo.gift.item.ItemSku;
import com.chouchongkeji.service.gift.item.vo.SkuListVo;

import java.util.List;

public interface ItemSkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemSku record);

    int insertSelective(ItemSku record);

    ItemSku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemSku record);

    int updateByPrimaryKey(ItemSku record);

    List<SkuListVo> selectByItemId(Integer itemId);

    ItemSku selectBySkuId(Integer skuId);

}