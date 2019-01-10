package com.chouchongkeji.mvc.controller.mall.article;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.mall.article.ArticleService;
import com.chouchongkeji.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yy
 * @date 2018/6/11
 **/

@RestController
@RequestMapping("noauth/v1/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 获得文章列表
     *
     * @param: [userDetails 用户认证信息, page 分页信息,type 1-banner,2-星座，2普通文章]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    @PostMapping("list")
    public Response getArticleList(PageQuery page, Byte type) {
        if (type == null || (type != Constants.ARTICLE_TYPE.BANNER && type != Constants.ARTICLE_TYPE.SIGN
                && type != Constants.ARTICLE_TYPE.ARTICLE)) {
            return ResponseFactory.errMissingParameter();
        }
        return articleService.getArticleList(page, type);
    }

    /**
     * 获取文章详情
     *
     * @param: [userDetails 用户认证信息, id 文章id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    @RequestMapping("detail")
    public Response getArticleDetail(Integer id) {
        if (id == null) {
            return ResponseFactory.errMissingParameter();
        }
        return articleService.getArticleDetail(id);
    }

    /**
     * 获得文章html
     *
     * @param: [id 文章id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    @GetMapping("html")
    public Response getHtmlItemDetail(Integer id) {
        if (id == null) {
            return ResponseFactory.errMissingParameter();
        }
        return articleService.getHtmlItemDetail(id);
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
    @PostMapping("item_list")
    public Response getArticleItem(Integer id, PageQuery pageQuery) {
        if (id == null){
            return ResponseFactory.errMissingParameter();
        }
        return articleService.getArticleItem(id,pageQuery);
    }

}
