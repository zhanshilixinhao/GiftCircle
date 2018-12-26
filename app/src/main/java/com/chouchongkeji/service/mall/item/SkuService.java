package com.chouchongkeji.service.mall.item;

import com.chouchongkeji.goexplore.common.Response;
import com.yichen.auth.service.UserDetails;

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


    /**
     * 根据sku获取库存
     *
     * @param skuId
     * @return
     * @author linqin
     * @date 2018/6/15
     */
    Response getSkuStock(Integer skuId);

    /**
     * 根据skuId 获取商品详情
     * @param skuId
     * @return
     * @author linqin
     * @date 2018/6/15
     */
    Response itemDetail(UserDetails userDetails, Integer skuId);
}
