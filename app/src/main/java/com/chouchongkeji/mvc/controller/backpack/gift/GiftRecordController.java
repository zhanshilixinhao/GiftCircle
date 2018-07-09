package com.chouchongkeji.mvc.controller.backpack.gift;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.backpack.gift.GiftRecordService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 人情账簿
 *
 * @author yichenshanren
 * @date 2018/7/9
 */

@RestController
@RequestMapping("auth/v1/gift/record")
public class GiftRecordController {

    @Autowired
    private GiftRecordService giftRecordService;

    /**
     * 获取一个用户的总的收支
     *
     * @param userDetails 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/7/9
     */
    @PostMapping("inex")
    public Response getInEx(@AuthenticationPrincipal UserDetails userDetails) {
        return giftRecordService.getInEx(userDetails.getUserId());
    }

}
