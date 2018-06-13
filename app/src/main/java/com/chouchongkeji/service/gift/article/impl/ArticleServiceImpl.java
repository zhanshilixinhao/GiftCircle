package com.chouchongkeji.service.gift.article.impl;

import com.chouchongkeji.dao.gift.article.ArticleMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.pojo.gift.article.Article;
import com.chouchongkeji.service.gift.article.ArticleService;
import com.chouchongkeji.service.gift.article.vo.ArticleDetail;
import com.chouchongkeji.service.gift.article.vo.ArticleVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yy
 * @date 2018/6/11
 **/

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 获得文章列表
     *
     * @param: [userId 用户id, page 分页信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    @Override
    public Response getArticleList(PageQuery page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Integer status = 1;
        List<ArticleVo> articleVos = articleMapper.selectByStatus(status);
        return ResponseFactory.sucData(articleVos);
    }

    /**
     * 获得文章详情
     *
     * @param: [userId 用户id, id 文章id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    @Override
    public Response getArticleDetail(Integer id) {
       Article article = articleMapper.selectByPrimaryKey(id);
       if (article != null) {
           ArticleDetail articleDetail = new ArticleDetail();
           articleDetail.setCreated(article.getCreated());
           articleDetail.setTitle(article.getTitle());
           articleDetail.setDetail(article.getDetail());
           return ResponseFactory.sucData(articleDetail);
       } else {
           return ResponseFactory.err("无此文章");
       }
    }
}
