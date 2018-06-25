package com.chouchongkeji.mvc.controller.gift.item;

import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.gift.item.CartService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2018/6/13
 */
@RestController
@RequestMapping("/auth/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 购物车列表
     *
     * @param userDetails
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    @PostMapping("/list")
    public Response getCartList(@AuthenticationPrincipal UserDetails userDetails) {
        return cartService.getCartList(userDetails.getUserId());
    }

    /**
     * 商品加入购物车
     *
     * @param userDetails
     * @param skuId 商品最小销售单元id
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    @PostMapping("/add_item")
    public Response addItemToCart(@AuthenticationPrincipal UserDetails userDetails, Integer skuId) {
        if (skuId == null) {
            return ResponseFactory.err("参数错误");
        }
        return cartService.addItemToCart(userDetails.getUserId(), skuId);
    }


    /**
     * 增加或减少购物车商品数量
     *
     * @param userDetails
     * @param skuId 商品最小销售单元id
     * @param change 增加或减少商品数量
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    @PostMapping("quantity")
    public Response changeQuantity(@AuthenticationPrincipal UserDetails userDetails, Integer skuId ,Integer change) {
        if (skuId == null) {
            return ResponseFactory.err("参数错误");
        }
        if (change==null){
            return ResponseFactory.err("参数错误");
        }
        return cartService.changeQuantity(userDetails.getUserId(), skuId,change);
    }


    /**
     * 删除购物车商品
     *
     * @param userDetails
     * @param skuId 商品最小销售单元id
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    @PostMapping("/delete")
    public Response deleteItem(@AuthenticationPrincipal UserDetails userDetails, Integer skuId) {
        if (skuId == null) {
            return ResponseFactory.err("参数错误");
        }
        return cartService.deleteItem(userDetails.getUserId(), skuId);
    }

}
