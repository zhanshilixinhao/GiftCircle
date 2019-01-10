package com.chouchongkeji.dial.dao.gift.item;

import com.chouchongkeji.dial.pojo.gift.item.LabelItem;
import com.chouchongkeji.service.mall.item.vo.ItemListVo;

import java.util.List;

public interface LabelItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LabelItem record);

    int insertSelective(LabelItem record);

    LabelItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LabelItem record);

    int updateByPrimaryKey(LabelItem record);

    /**
     * 根据标签id获取商品
     * @param id
     * @return
     */
    List<ItemListVo> selectByLabelId(Integer id);
}
