package com.chouchongkeji.mvc.controller.backpack.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.item.ReceiveItemService;
import com.yichen.auth.mvc.AppClient;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
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
     * 商品提货之前检查商品是否下架或删除
     * @param userDetails
     * @param bpId 背包id
     * @return
     * @author linqin
     * @date 2019/1/30
     */
    @PostMapping("check")
    public Response checkItemStatus(@AuthenticationPrincipal UserDetails userDetails, Long bpId) {
        if (bpId == null){
            return ResponseFactory.errMissingParameter();
        }
        return receiveItemService.checkItemStatus(userDetails.getUserId(),bpId);
    }


    /**
     * 创建提货订单
     *
     * @param userDetails
     * @param bpId        背包商品id
     * @param shippingId  收货地址id
     * @return
     * @author linqin
     * @date 2018/6/28
     */
    @PostMapping("order")
    public Response createReceiveOrder(@AuthenticationPrincipal UserDetails userDetails, @AppClient Integer client,
                                       Long bpId, Integer shippingId) {
        //校验参数
        if (bpId == null) {
            return ResponseFactory.err("参数错误");
        }
        if (shippingId == null) {
            return ResponseFactory.err("参数错误");
        }
        return receiveItemService.createOrder(userDetails.getUserId(), client, bpId, shippingId);
    }


    /**
     * 提货订单列表
     *
     * @param userDetails
     * @param pageQuery
     * @param status      订单状态，0-全部，1-未完成(1待发货,2已发货)；2-已完成（3已收货待评价，4已评价）
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @PostMapping("list")
    public Response orderList(@AuthenticationPrincipal UserDetails userDetails, PageQuery pageQuery, Integer status) {
        //校验参数
        if (status == null) {
            status = 0;
        }
        if (status < 0 || status > 2) {
            return ResponseFactory.err("参数错误");
        }
        return receiveItemService.getOrderList(userDetails.getUserId(), pageQuery, status);
    }


    /**
     * 提货订单详情
     *
     * @param userDetails
     * @param orderNo     提货订单号
     * @return
     * @author linqin
     * @date 2018/6/29
     */
    @PostMapping("detail")
    public Response getOrderDetail(@AuthenticationPrincipal UserDetails userDetails, Long orderNo) {
        //参数校验
        if (orderNo == null) {
            return ResponseFactory.err("参数错误");
        }
        return receiveItemService.getOrderDetail(userDetails.getUserId(), orderNo);
    }

    /**
     * 提货订单状态处理,确认收货
     *
     * @param userDetails
     * @param orderNo     提货订单号
     * @return
     * @author linqin
     * @date 2018/6/29
     */
    @PostMapping("confirm")
    public Response confirmOrder(@AuthenticationPrincipal UserDetails userDetails, Long orderNo) {
        //参数校验
        if (orderNo == null) {
            return ResponseFactory.err("参数错误");
        }
        return receiveItemService.confirmOrder(userDetails.getUserId(), orderNo);
    }

    /**
     * 提货订单状态处理,评论订单
     *
     * @param userDetails
     * @param orderNo     提货订单号
     * @param star        评价星星
     * @param content     评论文字
     * @param pictures    评论照片
     * @return
     * @author linqin
     * @date 2018/6/30
     */
    @PostMapping("comment")
    public Response commentOrder(@AuthenticationPrincipal UserDetails userDetails, Long orderNo, Float star,
                                 String content, String pictures) {
        //参数校验
        if (orderNo == null) {
            return ResponseFactory.err("参数错误");
        }
        if (star == null) {
            return ResponseFactory.err("请对宝贝做出相符描述");
        }
        if (StringUtils.isBlank(content)) {
            return ResponseFactory.err("请对宝贝做出相符描述");
        }
        return receiveItemService.commentOrder(userDetails.getUserId(), orderNo, star.intValue(), content, pictures);
    }


}
