package com.chouchongkeji.service.mall.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.mall.item.vo.OrderVo;
import com.chouchongkeji.service.mall.item.vo.SkuListVo;

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
    Response createOrder(Integer userId, Integer client, HashSet<OrderVo> skus,Integer payWay,Byte isShoppingCart);

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

    /**
     * 更新销量和详细订单状态
     * @param orderNo 订单号
     * @author linqin
     * @date 2018/7/5
     */
    int updateStatusSales(Long orderNo);

    /**
     * 取出规格信息
     *
     * @param skuListVo
     * @return
     */
    String genTitle(SkuListVo skuListVo);

    /**
     * 查看是否是被邀请进来
     * @param userId
     * @param count
     * @param itemOrderId
     * @param orderNo
     * @return
     */
    int parentUserFirework(Integer userId,Integer count,Integer itemOrderId,Long orderNo);
}
