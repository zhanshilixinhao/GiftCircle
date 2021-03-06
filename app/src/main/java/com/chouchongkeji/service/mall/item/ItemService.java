package com.chouchongkeji.service.mall.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.yichen.auth.service.UserDetails;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2018/6/12
 */
public interface ItemService {
    /**
     * 商品分类列表
     *
     * @return
     * @author linqin
     * @date 2018/6/12
     */
    Response getCategoryList();

    /**
     *商品列表查询
     *
     * @param classes 查询类型 0-默认，1-精选，2-热门
     * @param gender  筛选性别 0-默认，1-男，2-女
     * @param minAge  最小年龄
     * @param maxAge  最大年龄
     * @param minPrice  最低价格
     * @param maxPrice  最高价格
     * @param eventId 事件id
     * @return
     * @author linqin
     *  @date 2018/6/12
     */
    Response getItemList(Integer classes, Integer gender, Integer minAge, Integer maxAge, BigDecimal minPrice, BigDecimal maxPrice, Integer eventId,
                         PageQuery pageQuery,Integer categoryId,Byte priceRank,Byte acuraRank,String keywords,Integer isPage);

    /**
     * 获取商品详情
     *
     * @param: [id 商品id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    Response getItemDetail(UserDetails userDetails, Integer id);

    /**
     * 获得活动的html详情
     *
     * @param: [id 商品id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    Response getHtmlItemDetail(Integer id);

    /**
     *获取商品评论列表
     *
     * @param: [id 商品, page 分页信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    Response getItemCommentList(Integer id, PageQuery page);

    /**
     * 商品搜索
     * @param keyword 关键字
     * @return
     * @author: linqin
     * @Date: 2018/7/6
     */
    Response searchItem(String keyword);
    /**
     * 商品文章搜索
     *
     * @param keyword 关键字
     * @return
     * @author: linqin
     * @Date: 2018/7/6
     */
    Response searchItemArticle(String keyword);


    /**
     * 一级分类列表
     *
     * @return
     * @author linqin
     * @date 2019/6/20
     */
    Response stairCategoryList();

    /**
     * 二级分类列表
     *
     * @param pid 父级id
     * @return
     * @author linqin
     * @date 2019/6/20
     */
    Response secondCategoryList(Integer pid);


    /**
     * 更多分类
     * @return
     * @author linqin
     * @date 2018/6/12
     */
    Response moreCategory();
}
