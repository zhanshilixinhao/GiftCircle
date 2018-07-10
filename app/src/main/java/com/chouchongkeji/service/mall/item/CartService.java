package com.chouchongkeji.service.mall.item;

import com.chouchongkeji.goexplore.common.Response;

import java.util.HashSet;

/**
 * @author linqin
 * @date 2018/6/13
 */
public interface CartService {
    /**
     * 购物车列表
     *
     * @param userId
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    Response getCartList(Integer userId);

    /**
     * 商品加入购物车
     * @param userId
     * @param skuId
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    Response addItemToCart(Integer userId, Integer skuId , Integer quantity);

    /**
     * 增加或减少购物车商品数量
     ** @param skuId 商品最小销售单元id
     * @param userId 用户id
     * @param change 增加或减少商品数量
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    Response changeQuantity(Integer userId, Integer skuId,Integer change);

    /**
     * 删除购物车商品
     *
     * @param userId
     * @param skuId 商品最小销售单元id
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    Response deleteItem(Integer userId, HashSet skuId);
}
