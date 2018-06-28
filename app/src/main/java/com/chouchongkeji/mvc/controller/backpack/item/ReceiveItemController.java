package com.chouchongkeji.mvc.controller.backpack.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.item.ReceiveItemService;
import com.yichen.auth.mvc.AppClient;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2018/6/22
 */
@RestController
@RequestMapping("auth/receive/item")
public class ReceiveItemController {

    @Autowired
    private ReceiveItemService receiveItemService;

    /**
     * 创建提货订单
     * @param userDetails
     * @param bpItemId 背包商品id
     * @param shippingId 收货地址id
     * @return
     * @author linqin
     * @date 2018/6/28
     */
    @PostMapping("order")
    public Response createReceiveOrder(@AuthenticationPrincipal UserDetails userDetails, @AppClient Integer client,
                                       Integer bpItemId,Integer shippingId){
        //校验参数
        if (bpItemId == null){
            return ResponseFactory.err("参数错误");
        }
        if (shippingId == null){
            return ResponseFactory.err("参数错误");
        }
        return receiveItemService.createOrder(userDetails.getUserId(),client,bpItemId ,shippingId);
    }




    /**
     * 提货订单列表
     *
     * @param userDetails
     * @param pageQuery
     * @param status 订单状态，1-待发货；2-已发货；3-已收货,待评价，4-已评价,5-取消，6-删除
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @PostMapping("list")
    public Response orderList(@AuthenticationPrincipal UserDetails userDetails, PageQuery pageQuery,Integer status){
        //校验参数

        return receiveItemService.getOrderList(userDetails.getUserId(),pageQuery,status);
    }


}
