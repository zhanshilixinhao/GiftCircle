package com.chouchongkeji.dial.dao.gift.article;

import com.chouchongkeji.dial.pojo.gift.article.ArticleScene;

import java.util.List;

public interface ArticleSceneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleScene record);

    int insertSelective(ArticleScene record);

    ArticleScene selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleScene record);

    int updateByPrimaryKey(ArticleScene record);

    List<ArticleScene> selectByAll();
}
