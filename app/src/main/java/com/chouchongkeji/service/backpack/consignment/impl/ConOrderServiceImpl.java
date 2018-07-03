package com.chouchongkeji.service.backpack.consignment.impl;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.backpack.consignment.ConOrderService;
import com.chouchongkeji.util.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linqin
 * @date 2018/7/3
 */
@Service
public class ConOrderServiceImpl implements ConOrderService {

    @Autowired
    private OrderHelper orderHelper;

    /**
     * 寄售台订单创建
     *
     * @param userId 用户id
     * @param client
     * @param consignmentId 寄售台Id
     * @param payWay        支付方式  微信 24656  ，支付宝78990   ，余额 98001
     * @return
     * @author linqin
     *  @date 2018/7/3
     */
    @Override
    public Response createOrder(Integer userId, Integer client, Integer consignmentId, Integer payWay) {
        //订单号
        Long orderNo = orderHelper.genOrderNo(client,5);
        //创建订单
//        ConsignmentOrder

        return null;
    }
}
