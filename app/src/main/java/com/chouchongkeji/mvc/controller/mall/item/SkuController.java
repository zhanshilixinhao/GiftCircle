package com.chouchongkeji.mvc.controller.mall.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.mall.item.SkuService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.POST;

/**
 * @author linqin
 * @date 2018/6/15
 */
@RestController
@RequestMapping("/noauth/item")
public class SkuController {

    @Autowired
    private SkuService skuService;

    /**
     * 获取商品的sku组合
     *
     * @param itemId 商品id
     * @return
     * @author linqin
     * @date 2018/6/15
     */
    @PostMapping("sku_set")
    public Response getSkuSet(Integer itemId) {
        //校验商品是否存在
        if (itemId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return skuService.getSkuSet(itemId);
    }

    /**
     * 根据sku获取库存
     *
     * @param skuId
     * @return
     * @author linqin
     * @date 2018/6/15
     */
    @PostMapping("sku_stock")
    public Response getSkuStock(Integer skuId) {
        if (skuId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return skuService.getSkuStock(skuId);
    }

    /**
     * 根据skuId 获取商品详情
     * @param skuId
     * @return
     * @author linqin
     * @date 2018/6/15
     */
    @PostMapping("detail")
    public Response itemDetail(@AuthenticationPrincipal UserDetails userDetails, Integer skuId) {
        if (skuId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return skuService.itemDetail(userDetails,skuId);
    }
}
