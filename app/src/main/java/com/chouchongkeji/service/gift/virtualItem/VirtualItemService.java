package com.chouchongkeji.service.gift.virtualItem;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

public interface VirtualItemService {
    /**
     * 获得虚拟商品分类列表
     *
     * @param: [userId 用户id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    Response getVirItemCategoryList(Integer userId);

    /**
     * 获得虚拟商品列表
     *
     * @param: [userId 用户id, id, 虚拟商品分类id, page 分页信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    Response getVirItemList(Integer userId, Integer id, PageQuery page);
}
