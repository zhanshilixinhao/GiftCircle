package com.chouchongkeji.mvc.controller.home;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.home.WelfareService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2019/2/20 14:47
 */
@RestController
@RequestMapping("auth/welfare")
public class WelfareController {

    @Autowired
    private WelfareService welfareService;


    /**
     * 确认领取福利
     *
     * @param userDetails
     * @return
     * @author linqin
     * @date 2019/2/20
     */
    @PostMapping("confirm")
    public Response confirmWelfare(@AuthenticationPrincipal UserDetails userDetails) {
        return welfareService.confirmWelfare(userDetails.getUserId());
    }



    /**
     * 获取整点福利
     *
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    @PostMapping("list")
    public Response getWelfare(@AuthenticationPrincipal UserDetails userDetails) {
        return welfareService.getWelfare(userDetails.getUserId());
    }


}
