package com.chouchongkeji.mvc.controller.backpack.gift;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.gift.vo.GiftSendVo;
import com.chouchongkeji.service.backpack.gift.GiftService;
import com.yichen.auth.mvc.AppClient;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yichenshanren
 * /@date 2018/7/2
 */

@RestController
@RequestMapping("auth/v1/gift")
public class GiftController {

    @Autowired
    private GiftService giftService;

    /**
     * app赠送礼物实现
     *
     * @param userDetails 赠送者信息
     * @param sendVo      赠送礼物信息
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @RequestMapping("sendForApp")
    public Response sendForApp(@AuthenticationPrincipal UserDetails userDetails,
                               @AppClient Integer client,
                               GiftSendVo sendVo, String s2, String de) {
        if (sendVo.getBpId() == null ||
                sendVo.getFriendUserId() == null ||
                sendVo.getType() == null ||
                StringUtils.isAnyBlank(sendVo.getGreeting(), sendVo.getEvent(), s2, de) ||
                (sendVo.getType() == 2 && (sendVo.getTargetTime() == null ||
                        sendVo.getTargetTime().getTime() < System.currentTimeMillis() + 60000))) {
            return ResponseFactory.errMissingParameter();
        }
        return giftService.sendForApp(userDetails.getUserId(), sendVo, de, s2, client);
    }

    /**
     * 答谢礼物赠送
     *
     * @param userDetails    赠送者信息
     * @param recordDetailId 记录详情id
     * @param reply          回复内容
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @RequestMapping("acknowledge")
    public Response acknowledge(@AuthenticationPrincipal UserDetails userDetails,
                               Integer recordDetailId, String reply) {
        if (recordDetailId == null || StringUtils.isBlank(reply)) {
            return ResponseFactory.errMissingParameter();
        }
        return giftService.acknowledge(userDetails.getUserId(), recordDetailId, reply);
    }
}
