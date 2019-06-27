package com.chouchongkeji.dial.dao.gift.article;

import com.chouchongkeji.dial.pojo.gift.article.Article;
import com.chouchongkeji.service.mall.article.vo.ArticleVo;
import com.chouchongkeji.service.mall.article.vo.ArticleWxVo;
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
     * @return: java.util.List<com.chouchongkeji.service.mall.article.vo.ArticleVo>
     * @author: yy
     * @Date: 2018/6/11
     */
    List<ArticleVo> selectByStatus(@Param("status") Integer status,@Param("type") Byte type );

    /**
     * 按天获取文章列表
     * @param
     * @return
     */
    List<ArticleVo> selectByDay(@Param("start") Long start,@Param("end") Long end);

    /**
     * 根据关键字查询文章
     * @param keyword
     * @return
     */
    List<Article> selectArticleList(String keyword);

    /**
     * 微信文章搜索
     * @param sceneId
     * @param labelId
     * @param festivalId
     * @param keywords
     * @return
     */
    List<ArticleWxVo> selectBySearch(@Param("sceneId") Integer sceneId,@Param("labelId") Integer labelId,@Param("festivalId") Integer festivalId,@Param("keywords") String keywords);
}
