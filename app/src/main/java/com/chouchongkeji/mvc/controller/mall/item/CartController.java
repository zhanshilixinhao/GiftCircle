package com.chouchongkeji.mvc.controller.mall.item;

import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.utils.Utils;
import com.chouchongkeji.service.mall.item.CartService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

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
     * @param skuId       商品最小销售单元id
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    @PostMapping("/add_item")
    public Response addItemToCart(@AuthenticationPrincipal UserDetails userDetails, Integer skuId, Integer quantity) {
        if (skuId == null) {
            return ResponseFactory.err("参数错误");
        }
        if (quantity == null) {
            quantity = 1;
        } else {
            if (quantity < 1) {
                return ResponseFactory.errMissingParameter();
            }
        }
        return cartService.addItemToCart(userDetails.getUserId(), skuId, quantity);
    }


    /**
     * 增加或减少购物车商品数量
     *
     * @param userDetails
     * @param skuId       商品最小销售单元id
     * @param change      增加或减少商品数量
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    @PostMapping("quantity")
    public Response changeQuantity(@AuthenticationPrincipal UserDetails userDetails, Integer skuId, Integer change) {
        if (skuId == null) {
            return ResponseFactory.err("参数错误");
        }
        if (change == null) {
            return ResponseFactory.err("参数错误");
        }
        return cartService.changeQuantity(userDetails.getUserId(), skuId, change);
    }


    /**
     * 删除购物车商品
     *
     * @param userDetails
     * @param skuId       商品最小销售单元id
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    @PostMapping("/delete")
    public Response deleteItem(@AuthenticationPrincipal UserDetails userDetails, String skuId) {
        if (skuId == null) {
            return ResponseFactory.err("参数错误");
        }
        HashSet<Integer> ids = new HashSet<>();
        boolean ids1 = Utils.getIds(skuId, ids);//把,隔开的字符串转换为集合
        if (ids1) {
            return ResponseFactory.err("id错误");
        }
        return cartService.deleteItem(userDetails.getUserId(), ids);
    }

}
