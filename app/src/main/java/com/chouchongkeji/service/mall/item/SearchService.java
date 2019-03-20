package com.chouchongkeji.service.mall.item;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author linqin
 * @date 2019/3/20 10:17
 */

public interface SearchService {

    /**
     * 商品文章事件搜索
     *
     * @param keyword 关键字
     * @return
     * @author: linqin
     * @Date: 2018/7/6
     */
    Response searchItemArticle(Integer userId, String keyword,Integer type);
}
