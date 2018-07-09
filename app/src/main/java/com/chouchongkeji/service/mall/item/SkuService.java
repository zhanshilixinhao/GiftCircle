package com.chouchongkeji.service.mall.item;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author linqin
 * @date 2018/6/15
 */
public interface SkuService {

    /**
     * 获取商品的sku组合
     *
     * @param itemId 商品id
     * @return
     * @author linqin
     * @date 2018/6/15
     */
    Response getSkuSet(Integer itemId);
}
