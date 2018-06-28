package com.chouchongkeji.service.gift.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.gift.item.vo.OrderVo;

import java.util.HashSet;

/**
 * @author linqin
 * @date 2018/6/20
 */
public interface OrderService {
    /**
     * 创建商品订单
     * @param userId
     * @param client
     * @param skus
     * @return
     * @author linqin
     * @date 2018/6/20
     */
    Response createOrder(Integer userId, Integer client, HashSet<OrderVo> skus,Integer payWay);

    /**
     * 订单支付
     * @param userId   用户id
     * @param orderNo  订单号
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    Response orderPay(Integer userId, Long orderNo ,Integer payWay);

    /**
     * 订单取消
     * @param userId 用户id
     * @param orderNo 订单号
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    Response cancelOrder(Integer userId, Long orderNo);

    /**
     * 订单列表
     * @param userId 用户Id
     * @param pageQuery 分页
     * @param status   状态 1-未完成（未付款），2-已完成（已付款）
     * @return
     * @author linqin
     *  @date 2018/6/21
     */
    Response orderList(Integer userId, PageQuery pageQuery,Integer status);
}
