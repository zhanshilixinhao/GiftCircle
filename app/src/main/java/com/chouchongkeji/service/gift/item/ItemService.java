package com.chouchongkeji.service.gift.item;

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
     * @param classes 查询类型 1-精选，2-热门，3-新品
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
    Response getItemList(Integer classes, Integer gender, Integer minAge, Integer maxAge, BigDecimal minPrice,
                         BigDecimal maxPrice, Integer eventId, PageQuery pageQuery);

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
    Response getHtmlItemDetail(Integer id);}
