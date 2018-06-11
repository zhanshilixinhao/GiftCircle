package com.chouchongkeji.dao.gift.article;

import com.chouchongkeji.pojo.gift.article.Article;
import com.chouchongkeji.service.gift.article.vo.ArticleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    /**
     * 通过状态查询文章列表
     *
     * @param: [status 文章状态]
     * @return: java.util.List<com.chouchongkeji.service.gift.article.vo.ArticleVo>
     * @author: yy
     * @Date: 2018/6/11
     */
    List<ArticleVo> selectByStatus(@Param("status") Integer status);
}