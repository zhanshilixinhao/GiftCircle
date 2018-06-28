package com.chouchongkeji.service.backpack.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

/**
 * @author linqin
 * @date 2018/6/22
 */
public interface ReceiveItemService {
    /**
     * 提货订单列表
     *
     * @param userId
     * @param pageQuery
     * @param status 订单状态，1-待发货；2-已发货；3-已收货,待评价，4-已评价,5-取消，6-删除
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    Response getOrderList(Integer userId, PageQuery pageQuery,Integer status);

    /**
     * 创建提货订单
     * @param userId 用户id
     * @param bpItemId 背包商品id
     * @param shippingId 收货地址id
     * @return
     * @author linqin
     * @date 2018/6/28
     */
    Response createOrder(Integer userId,Integer client, Integer bpItemId,Integer shippingId);
}
