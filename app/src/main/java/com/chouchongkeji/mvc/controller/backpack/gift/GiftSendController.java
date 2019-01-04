package com.chouchongkeji.mvc.controller.backpack.gift;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.gift.GiftSendService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 礼物赠送记录
 *
 * @author linqin
 * @date 2019/1/4 11:46
 */
@RestController
@RequestMapping("auth/v1/gift_send")
public class GiftSendController {

    @Autowired
    private GiftSendService giftSendService;

    /**
     * 赠送礼物列表
     *
     * @param userDetails
     * @param flag        1 赠送记录 2 收礼记录
     * @return
     * @author linqin
     * @date 2019/1/4 11:46
     */
    @PostMapping("send_list")
    public Response sendList(@AuthenticationPrincipal UserDetails userDetails, Byte flag) {
        if (flag == null || (flag != 1 && flag != 2)) {
            return ResponseFactory.errMissingParameter();
        }
        return giftSendService.sendList(userDetails.getUserId(), flag);
    }

}
