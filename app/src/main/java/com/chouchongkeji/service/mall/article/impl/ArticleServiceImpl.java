package com.chouchongkeji.service.mall.article.impl;

import com.chouchongkeji.dial.dao.gift.article.ArticleItemMapper;
import com.chouchongkeji.dial.dao.gift.article.ArticleMapper;
import com.chouchongkeji.dial.pojo.gift.article.Article;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.service.mall.article.ArticleService;
import com.chouchongkeji.service.mall.article.vo.ArticleDetail;
import com.chouchongkeji.service.mall.article.vo.ArticleVo;
import com.chouchongkeji.service.mall.item.vo.ItemListVo;
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

    @Autowired
    private ServiceProperties serviceProperties;

    @Autowired
    private ArticleItemMapper  articleItemMapper;

    /**
     * 获得文章列表
     *
     * @param: [userId 用户id, page 分页信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    @Override
    public Response getArticleList(PageQuery page,Byte type) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Integer status = 1;
        List<ArticleVo> articleVos = articleMapper.selectByStatus(status,type);
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
            articleDetail.setDetail(serviceProperties.getArticleDetail() + article.getId());
            articleDetail.setId(id);
            articleDetail.setCover(article.getCover());
            return ResponseFactory.sucData(articleDetail);
        } else {
            return ResponseFactory.err("无此文章");
        }
    }

    /**
     * 获得文章html
     *
     * @param: [id 文章id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    @Override
    public Response getHtmlItemDetail(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article != null) {
            return ResponseFactory.sucData(article.getDetail());
        }
        return ResponseFactory.err("无此文章");
    }

    /**
     * 文章商品列表
     *
     * @param id        文章id
     * @param pageQuery
     * @return
     * @author: yy
     * @Date: 2018/6/13
     */
    @Override
    public Response getArticleItem(Integer id, PageQuery pageQuery) {
        //分页
        PageHelper.startPage(pageQuery.getPageNum(),pageQuery.getPageSize());
        // 根据文章id查询商品列表
        List<ItemListVo> list = articleItemMapper.selectItemById(id);
        return ResponseFactory.sucData(list);
    }
}
