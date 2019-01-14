package com.chouchongkeji.mvc.controller.mall.article;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.mall.article.ArticleService;
import com.chouchongkeji.util.Constants;
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
             start = time(System.currentTimeMillis());
              end = timeEnd(System.currentTimeMillis());
        } else {
             start = time(day);
             end = timeEnd(day);
        }
        return articleService.getArticleByDay(start,end);
    }

    /**
     * 时间戳(当天0点)
     */
    public Long time(Long day) throws ParseException {
        Date now = new Date(day);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(now);//日期
        Date parse = dateFormat.parse(format);  //时间戳
        day = parse.getTime() / 1000;
        return day;
    }

    /**
     * 时间戳（当天12点）
     */
    public Long timeEnd(Long end) throws ParseException {
        Date now = new Date(end);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(now);
        Date parse = dateFormat.parse(format);
        end = DateUtils.addDays(parse, 1).getTime()/1000;
        return end;
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

}
