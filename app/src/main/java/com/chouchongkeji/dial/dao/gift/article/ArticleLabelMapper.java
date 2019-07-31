package com.chouchongkeji.dial.dao.gift.article;

import com.chouchongkeji.dial.pojo.gift.article.ArticleLabel;

import java.util.List;

public interface ArticleLabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleLabel record);

    int insertSelective(ArticleLabel record);

    ArticleLabel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleLabel record);

    int updateByPrimaryKey(ArticleLabel record);

    List<ArticleLabel> selectByAll();
}
