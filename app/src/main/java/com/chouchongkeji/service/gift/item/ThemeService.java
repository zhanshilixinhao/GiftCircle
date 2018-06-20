package com.chouchongkeji.service.gift.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

public interface ThemeService {
    /**
     * 获取主题列表
     *
     * @param: []
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    Response getThemeList();

    /**
     * 获取主题商品列表
     *
     * @param: [id, page]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    Response getThemeItemList(Integer id, PageQuery page);
}
