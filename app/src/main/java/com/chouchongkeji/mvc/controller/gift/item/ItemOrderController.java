package com.chouchongkeji.mvc.controller.gift.item;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.gift.item.OrderService;
import com.chouchongkeji.service.gift.item.vo.OrderVo;
import com.chouchongkeji.util.Constants;
import com.yichen.auth.mvc.AppClient;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

/**
 * @author linqin
 * @date 2018/6/20
 */
@RestController
@RequestMapping("auth/item/order")
public class ItemOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建商品订单
     * @param userDetails
     * @param client
     * @param skus json数组，数组格式
     *             [
     *             {
     *                "skuId":skuId,
     *             "quantity":quantity
     *             }
     *             ]
     * @return
     * @author linqin
     * @date 2018/6/20
     */
    @PostMapping("/create")
    public Response createOrder(@AuthenticationPrincipal UserDetails userDetails, @AppClient Integer client,
                                String skus,Integer payWay) {
        //检验支付方式
        if (payWay == null || (payWay != Constants.PAY_TYPE.WX && payWay != Constants.PAY_TYPE.ALI)) {
            return ResponseFactory.err("支付方式错误!");
        }
        //json数组转换
        HashSet<OrderVo> orderVos = JSON.parseObject(skus, new TypeReference<HashSet<OrderVo>>() {
        });
        if (CollectionUtils.isEmpty(orderVos)) {
            return ResponseFactory.errMissingParameter();
        }
        //遍历
        for (OrderVo order : orderVos) {
            if (order.getQuantity()<1){
                return ResponseFactory.err("商品数量不能小于1");
            }
            if (order.getSkuId()==null){
                return ResponseFactory.err("");
            }
        }
        return orderService.createOrder(userDetails.getUserId(),client,orderVos,payWay);
    }


    /**
     * 订单支付
     * @param userDetails
     * @param orderNo  订单号
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    @PostMapping("pay")
    public Response orderPay(@AuthenticationPrincipal UserDetails userDetails,Long orderNo ,Integer payWay){
        //检验支付方式
        if (payWay == null || (payWay != Constants.PAY_TYPE.WX && payWay != Constants.PAY_TYPE.ALI)) {
            return ResponseFactory.err("支付方式错误!");
        }
        if (orderNo == null){
            return ResponseFactory.errMissingParameter();
        }
        return orderService.orderPay(userDetails.getUserId(),orderNo,payWay);
    }

    /**
     * 订单取消
     * @param userDetails
     * @param orderNo 订单号
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    @PostMapping("cancel")
    public Response cancelOrder(@AuthenticationPrincipal UserDetails userDetails,Long orderNo){
        //校验参数
        if (orderNo == null){
            return ResponseFactory.errMissingParameter();
        }
        return orderService.cancelOrder(userDetails.getUserId(),orderNo);
    }


    /**
     * 订单列表
     * @param userDetails
     * @param pageQuery 分页
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    @PostMapping("list")
    public Response orderList(@AuthenticationPrincipal UserDetails userDetails, PageQuery pageQuery){
        return orderService.orderList(userDetails.getUserId(),pageQuery);
    }

}
