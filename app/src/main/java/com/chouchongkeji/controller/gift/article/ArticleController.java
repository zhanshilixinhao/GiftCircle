package com.chouchongkeji.controller.gift.article;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.gift.article.ArticleService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
     * @param: [userDetails 用户认证信息, page 分页信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    @PostMapping("list")
    public Response getArticleList(PageQuery page) {
        return articleService.getArticleList(page);
    }

    /**
     * 获取文章详情
     *
     * @param: [userDetails 用户认证信息, id 文章id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    @PostMapping("detail")
    public Response getArticleDetail(Integer id) {
        return articleService.getArticleDetail(id);
    }
}
