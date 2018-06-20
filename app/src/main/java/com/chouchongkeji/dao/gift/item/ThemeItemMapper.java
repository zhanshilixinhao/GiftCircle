package com.chouchongkeji.dao.gift.item;

import com.chouchongkeji.pojo.gift.item.ThemeItem;
import com.chouchongkeji.service.gift.item.vo.ItemListVo;
import com.chouchongkeji.service.gift.item.vo.ThemeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThemeItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThemeItem record);

    int insertSelective(ThemeItem record);

    ThemeItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThemeItem record);

    int updateByPrimaryKey(ThemeItem record);

    /**
     * 获得主题商品列表
     *
     * @param: [id 主题id]
     * @return: java.util.List<com.chouchongkeji.service.gift.item.vo.ItemListVo>
     * @author: yy
     * @Date: 2018/6/20
     */
    List<ItemListVo> selectByThemeId(@Param("id") Integer id);
}