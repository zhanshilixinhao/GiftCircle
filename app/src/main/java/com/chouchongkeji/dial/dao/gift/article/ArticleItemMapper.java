package com.chouchongkeji.dial.dao.gift.article;

import com.chouchongkeji.dial.pojo.gift.article.ArticleItem;
import com.chouchongkeji.service.mall.item.vo.ItemListVo;

import java.util.List;

public interface ArticleItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleItem record);

    int insertSelective(ArticleItem record);

    ArticleItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleItem record);

    int updateByPrimaryKey(ArticleItem record);

    /**
     * 根据id查询文章列表
     * @param id
     * @return
     */
    List<ItemListVo> selectItemById(Integer id);
}
