package com.chouchongkeji.mvc.controller.mall.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.mall.item.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if (itemId ==null){
            return ResponseFactory.errMissingParameter();
        }
        return skuService.getSkuSet(itemId);
    }


}
