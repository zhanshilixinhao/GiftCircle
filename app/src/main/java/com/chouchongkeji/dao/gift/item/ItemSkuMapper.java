package com.chouchongkeji.dao.gift.item;

import com.chouchongkeji.pojo.gift.item.ItemSku;

public interface ItemSkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemSku record);

    int insertSelective(ItemSku record);

    ItemSku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemSku record);

    int updateByPrimaryKey(ItemSku record);


    ItemSku selectBySkuId(Integer skuId);

}