package com.chouchongkeji.service.mall.article;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

public interface ArticleService {
    /**
     * 获得文章列表
     *
     * @param: [userId 用户id, page 分页信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    Response getArticleList(PageQuery page,Byte type);

    /**
     * 获取文章详情
     *
     * @param: [userId 用户id, id 文章id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    Response getArticleDetail(Integer id);

    /**
     * 获得文章html
     *
     * @param: [id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    Response getHtmlItemDetail(Integer id);

    /**
     * 文章商品列表
     *
     * @param id        文章id
     * @param pageQuery
     * @return
     * @author: yy
     * @Date: 2018/6/13
     */
    Response getArticleItem(Integer id, PageQuery pageQuery);
}
