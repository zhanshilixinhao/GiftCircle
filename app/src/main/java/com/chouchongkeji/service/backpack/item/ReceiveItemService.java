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
     * @param status    订单状态，0-全部，1-未完成(待发货,已发货)；2-已完成（已收货待评价，已评价）
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    Response getOrderList(Integer userId, PageQuery pageQuery, Integer status);

    /**
     * 创建提货订单
     *
     * @param userId     用户id
     * @param bpItemId   背包商品id
     * @param shippingId 收货地址id
     * @return
     * @author linqin
     * @date 2018/6/28
     */
    Response createOrder(Integer userId, Integer client, Integer bpItemId, Integer shippingId);

    /**
     * 提货订单详情
     *
     * @param userId      用户id
     * @param orderNo 提货订单号
     * @return
     * @author linqin
     * @date 2018/6/28
     */
    Response getOrderDetail(Integer userId, Long orderNo);

    /**
     * 提货订单状态处理,确认收货
     *
     * @param userId
     * @param orderNo 提货订单号
     * @return
     * @author linqin
     * @date 2018/6/29
     */
    Response confirmOrder(Integer userId, Long orderNo);

    /**
     * 提货订单状态处理,评论订单
     *
     * @param userId
     * @param orderNo 提货订单号
     * @param star 评价星星
     * @param content 评论文字
     * @param pictures 评论照片
     * @return
     * @author linqin
     * @date 2018/6/30
     */
    Response commentOrder(Integer userId, Long orderNo, Integer star, String content, String pictures);
}
