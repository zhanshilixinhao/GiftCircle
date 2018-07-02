package com.chouchongkeji.service.backpack.base;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

/**
 * @author yichenshanren
 * @date 2018/7/2
 */

public interface BpService {

    /**
     * 背包列表
     *
     * @param userId 用户信息
     * @param type   1 物品 2 虚拟物品 3 优惠券
     * @param page
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    Response getList(Integer userId, Integer type, PageQuery page);
}
