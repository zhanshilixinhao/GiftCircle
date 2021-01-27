package com.chouchongkeji.mvc.controller.v4;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.v4.ShareCouponService;
import com.yichen.auth.service.UserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: LxH
 * @time: 2020/10/15 0015 下午 6:11
 */
@RestController
@RequestMapping("auth/v4/shareCoupon")
public class ShareCouponController {

    @Resource
    private ShareCouponService shareCouponService;

    @RequestMapping("test")
    public Response test(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseFactory.sucData(userDetails.getUserId());
    }

    /**
     * @Description: 查找分享券
     * @Author: LxH
     * @Date: 2020/10/22 15:45
     */
    @PostMapping("findShareCouponByStore")
    public Response findShareCouponByStore(Integer storeId){
        return shareCouponService.findShareCouponByStore(storeId);
    }

    /**
     * @Description: 获取拥有分享券的店铺
     * @Author: LxH
     * @Date: 2020/10/22 14:44
     */
    @PostMapping("findStore")
    public Response findStore(){
        return shareCouponService.findStore();
    }

    /**
     *
     *
     *@description: 用户领取分享劵
     *@author: LxH
     *@time: 2020/10/15 0015 下午 6:20
     *
     */
    @PostMapping("giveShareCoupon")
    public Response giveShareCoupon(@AuthenticationPrincipal UserDetails userDetails,Integer shareCouponId,Integer storeId){
        return shareCouponService.giveShareCoupon(userDetails.getUserId(),shareCouponId,storeId);
    }

    /**
     *
     *
     *@description: 获取用户分享劵列表
     *@author: LxH
     *@time: 2020/10/16 0016 上午 11:28
     *
     */
    @PostMapping("getShareCouponList")
    public Response getShareCouponList(@AuthenticationPrincipal UserDetails userDetails){
        return shareCouponService.getShareCouponList(userDetails.getUserId());
    }

    /**
     * @Description:
     * @Author: LxH
     * @Date: 2021/1/14 16:25
     */
    @PostMapping("findShareCoupon")
    public Response findShareCoupon(Integer shareCouponId){
        return shareCouponService.findShareCoupon(shareCouponId);
    }
}
