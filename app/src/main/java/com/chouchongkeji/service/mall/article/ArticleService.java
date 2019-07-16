package com.chouchongkeji.service.mall.article;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.yichen.auth.mvc.AppClient;
import org.springframework.web.bind.annotation.PostMapping;

public interface ArticleService {


    /**
     * 按天获取文章列表
     *
     * @param
     * @return
     * @author linqin
     * * @date 2018/7/6
     */
    Response getArticleByDay(Long start, Long end);

    /**
     * 获得文章列表
     *
     * @param: [userId 用户id, page 分页信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    Response getArticleList(@AppClient Integer client, PageQuery page, Byte type);

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

    /*--------------------------------------------------微信文章----------------------------------------------------------------------*/

    /**
     * 文章节日列表
     * @return
     */
    Response getFestivalListAll();


    /**
     * 文章场景列表
     * @return
     */
    Response getSceneListAll();


    /**
     * 微信文章搜索
     * @param sceneId 场景id
     * @param labelId 对象id
     * @param festivalId 节日id
     * @param keywords 关键字
     * @return
     */
    Response wxArticleSearch(Integer sceneId, Integer labelId, Integer festivalId, String keywords);
}
