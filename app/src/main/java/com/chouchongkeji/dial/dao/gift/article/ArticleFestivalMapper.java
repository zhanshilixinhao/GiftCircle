package com.chouchongkeji.dial.dao.gift.article;

import com.chouchongkeji.dial.pojo.gift.article.ArticleFestival;

import java.util.List;

public interface ArticleFestivalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleFestival record);

    int insertSelective(ArticleFestival record);

    ArticleFestival selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleFestival record);

    int updateByPrimaryKey(ArticleFestival record);

    List<ArticleFestival> selectByAll();
}
