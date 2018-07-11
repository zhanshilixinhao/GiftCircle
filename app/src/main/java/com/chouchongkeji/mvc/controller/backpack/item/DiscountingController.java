package com.chouchongkeji.mvc.controller.backpack.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.item.DiscountingService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2018/7/11
 */
@RestController
@RequestMapping("auth/v1/discount")
public class DiscountingController {

    @Autowired
    private DiscountingService discountingService;


    /**
     * 背包物品折现
     * @param userDetails
     * @param bpId 背包id
     * @return
     * @author linqin
     * @date 2018/7/11
     */
    @PostMapping("record")
    public Response discountRecord(@AuthenticationPrincipal UserDetails userDetails, Integer bpId){
        //校验参数
        if (bpId == null){
            return ResponseFactory.errMissingParameter();
        }
        return discountingService.discountRecord(userDetails.getUserId(),bpId);
    }

}
