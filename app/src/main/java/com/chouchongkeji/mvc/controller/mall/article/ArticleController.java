package com.chouchongkeji.mvc.controller.mall.article;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.mall.article.ArticleService;
import com.chouchongkeji.util.Constants;
import com.chouchongkeji.util.TimeUtils;
import com.yichen.auth.mvc.AppClient;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * 按天获取文章列表
     *
     * @param day
     * @return
     * @author linqin
     * * @date 2018/7/6
     */
    @PostMapping("article_list")
    public Response getArticleByDay(Long day) throws ParseException {
        // 如果时间为空，默认为当天
        Long start;
        Long end;
        if (day == null) {
             start = TimeUtils.time(System.currentTimeMillis());
              end = TimeUtils.timeEnd(System.currentTimeMillis());
        } else {
             start = TimeUtils.time(day);
             end = TimeUtils.timeEnd(day);
        }
        return articleService.getArticleByDay(start,end);
    }



    /**
     * 获得文章列表
     *
     * @param: [userDetails 用户认证信息, page 分页信息,type 1-banner,2-星座，3首页普通文章 ，4商城首页文章]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    @PostMapping("list")
    public Response getArticleList(PageQuery page, Byte type) {
        if (type == null || (type != Constants.ARTICLE_TYPE.BANNER && type != Constants.ARTICLE_TYPE.SIGN
                && type != Constants.ARTICLE_TYPE.ARTICLE && type != Constants.ARTICLE_TYPE.MALL)) {
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

    /*--------------------------------------------------微信文章----------------------------------------------------------------------*/

    /**
     * 文章节日列表
     * @return
     */
    @PostMapping("festival_all")
    public Response getFestivalListAll(){
        return articleService.getFestivalListAll();
    }



    /**
     * 文章场景列表
     * @return
     */
    @PostMapping("scene_all")
    public Response getSceneListAll(){
        return articleService.getSceneListAll();
    }

    /**
     * 文章对象列表
     * @return
     */
    @PostMapping("label_all")
    public Response getLabelListAll(){
        return articleService.getLabelListAll();
    }


    /**
     * 微信文章搜索
     * @param sceneId 场景id 0 查询所有场景
     * @param labelId 对象id  0 所有
     * @param festivalId 节日id 0所有
     * @param keywords 关键字
     * @return
     */
    @PostMapping("search")
    public Response wxArticleSearch(Integer sceneId,Integer labelId,Integer festivalId, String keywords,PageQuery page){
        return articleService.wxArticleSearch(sceneId,labelId,festivalId,keywords,page);
    }



}
