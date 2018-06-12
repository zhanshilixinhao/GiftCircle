package com.chouchongkeji.service.gift.virtualItem;

import com.chouchongkeji.goexplore.common.Response;

public interface VirtualIteamOrderService {
    /**
     * 创建虚拟商品支付订单
     *
     * @param: [userId 用户id, client, id 虚拟商品id, quantity 数量]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/12
     */
    Response createVirOrder(Integer userId, Integer client, Integer id, Integer quantity);

    /**
     * 支付虚拟订单
     *
     * @param: [userId 用户id, payWay 支付方式, orderNo 订单号]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/12
     */
    Response payVirOrder(Integer userId, Integer payWay, Long orderNo);
}
