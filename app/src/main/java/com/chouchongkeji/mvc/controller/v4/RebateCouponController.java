package com.chouchongkeji.mvc.controller.v4;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.v4.RebateCouponService;
import com.yichen.auth.service.UserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Description: 折扣模塊
 * @Author Lxh
 * @Date 2020/10/21 11:13
 */
@RestController
@RequestMapping("auth/v4/rebateCoupon")
public class RebateCouponController {

    @Resource
    private RebateCouponService rebateCouponService;

    /**
     * @Description: 查询所有店铺
     * @Author: LxH
     * @Date: 2020/10/26 12:15
     */
    @PostMapping("findStores")
    public Response findStores(){
        return rebateCouponService.findStores();
    }


    /**
     * @Description: 創建折扣劵
     * @Author: LxH
     * @Date: 2020/10/21 11:23
     */
    @PostMapping("createRebateCoupon")
    public Response createRebateCoupon(@AuthenticationPrincipal UserDetails userDetails,Integer storeId){
        return rebateCouponService.createRebateCoupon(userDetails.getUserId(),storeId);
    }

    /**
     * @Description: 生成核销码
     * @Author: LxH
     * @Date: 2020/10/21 15:18
     */
    @PostMapping("appliedRebateCoupon")
    public Response appliedRebateCoupon(@AuthenticationPrincipal UserDetails userDetails,
                                        Long rebateCouponId){
        return rebateCouponService.appliedRebateCoupon(userDetails.getUserId(),rebateCouponId);
    }

    /**
     * @Description: 获取上级用户信息
     * @Author: LxH
     * @Date: 2020/11/16 11:43
     */
    @PostMapping("getSuperUserInfo")
    public Response getSuperUserInfo(Integer superId,Integer storeId){
        return rebateCouponService.getSuperUserInfo(superId,storeId);
    }

    /**
     * @Description: 助力好友
     * @Author: LxH
     * @Date: 2020/11/16 14:16
     */
    @PostMapping("helpUser")
    public Response helpUser(@AuthenticationPrincipal UserDetails userDetails,
                             Integer superId, BigDecimal userRebate,Integer storeId,BigDecimal rebateTopLimit){
        return rebateCouponService.addRebate(userRebate, userDetails.getUserId(), superId,storeId,rebateTopLimit);
    }
}
