package com.chouchongkeji.controller.gift.virtualItem;

import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.gift.virtualItem.VirtualIteamOrderService;
import com.chouchongkeji.util.Constants;
import com.yichen.auth.mvc.AppClient;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yy
 * @date 2018/6/12
 **/

@RestController
@RequestMapping("auth/v1/virOrder")
public class VirtualItemOrderController {
    @Autowired
    private VirtualIteamOrderService virtualIteamOrderService;

    /**
     * 创建虚拟商品订单
     *
     * @param: [userDetails 用户认证信息, client, id 虚拟商品id, quantity 数量, payWay 支付方式]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/12
     */
    @PostMapping("/create")
    public Response createVirOrder(@AuthenticationPrincipal UserDetails userDetails, @AppClient Integer client,
                                Integer id, Integer quantity) {
        //校验必传参数
        if (id == null || quantity == 0 || quantity < 1) {
            return ResponseFactory.err("商品信息错误");
        }
        return virtualIteamOrderService.createVirOrder(userDetails.getUserId(), client, id, quantity);
    }

    /**
     * 创建虚拟商品支付订单
     *
     * @param: [userDetails, payWay, orderNo]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    @PostMapping("/pay")
    public Response payVirOrder(@AuthenticationPrincipal UserDetails userDetails, Integer payWay, Long orderNo) {
        //校验必传参数
        if (orderNo == null) {
            return ResponseFactory.errMissingParameter();
        }
        //检验支付方式
        if (payWay == null || (payWay != Constants.PAY_TYPE.WX && payWay != Constants.PAY_TYPE.ALI)) {
            return ResponseFactory.err("支付方式错误!");
        }
        return virtualIteamOrderService.payVirOrder(userDetails.getUserId(), payWay, orderNo);
    }

}
