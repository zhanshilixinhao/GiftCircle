package com.chouchongkeji.mvc.controller.v4;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.v4.ElCouponListService;
import com.yichen.auth.service.UserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 礼包模块
 * @author: LxH
 * @time: 2020/10/17 0017 下午 3:44
 */
@RestController
@RequestMapping("auth/v4/elCouponList")
public class ElCouponListController {

    @Resource
    private ElCouponListService elCouponListService;

    /**
     * @Description: 获取礼包
     * @Author: LxH
     * @Date: 2020/10/22 16:23
     */
    @PostMapping("getStore")
    public Response getStore(Integer userMemberCardId){
        return elCouponListService.getStore(userMemberCardId);
    }

    /**
     *
     *
     *@description: 用户领取礼包
     *@author: LxH
     *@time: 2020/10/17 0017 下午 3:51
     *
     */
    @PostMapping("bindElCouponList")
    public Response bindElCouponListByUser(@AuthenticationPrincipal UserDetails userDetails,
                                           Integer elCouponListId , Byte thePaid){
        if (elCouponListId == null || thePaid == null) {
            return ResponseFactory.err("参数不能为空");
        }
        return elCouponListService.bindElCouponListByUser(userDetails.getUserId(),elCouponListId,thePaid);
    }

    /**
     * @Description: 查询是否弹礼包弹窗
     * @Author: LxH
     * @Date: 2021/1/25 9:15
     */
    @PostMapping("queryState")
    public Response queryState(@AuthenticationPrincipal UserDetails userDetails,
                               Integer elCouponListId ){
        if (elCouponListId == null) {
            return ResponseFactory.err("参数不能为空");
        }
        return elCouponListService.queryState(userDetails.getUserId(),elCouponListId);
    }

    /**
     * @Description: 扫码获取礼包详情
     * @Author: LxH
     * @Date: 2020/10/26 13:01
     */
    @PostMapping("getElCouponListById")
    public Response getElCouponListById(Integer id){
        return elCouponListService.getElCouponListById(id);
    }
}
