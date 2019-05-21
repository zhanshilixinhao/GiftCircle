package com.chouchongkeji.mvc.controller.backpack.gift;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.gift.vo.GiftSendVo;
import com.chouchongkeji.service.backpack.gift.GiftService;
import com.chouchongkeji.service.backpack.gift.vo.SendWXVo;
import com.chouchongkeji.service.backpack.gift.vo2.GiftSendVo2;
import com.yichen.auth.mvc.AppClient;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.POST;

import java.util.HashSet;

/**
 * @author yichenshanren
 * /@date 2018/7/2
 */

@RestController
@RequestMapping("auth/v1/gift")
public class GiftController {

    @Autowired
    private GiftService giftService;

    /*----------------------------------------礼物赠送v2开始---------------------------------------------------*/

    /**
     * app赠送礼物实现V2
     *
     * @param userDetails 赠送者信息
     * @param sendVo      赠送礼物信息 type 1 未领取 2 已领取部分 3 已领取全部 4 超时领取失败
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @RequestMapping("sendForAppV2")
    public Response sendForAppV2(@AuthenticationPrincipal UserDetails userDetails,
                                 @AppClient Integer client,
                                 GiftSendVo2 sendVo) {
        if (sendVo.getBpId() == null || // 主物品背包id
                sendVo.getFriendUserIds() == null || // 赠送好友id
                sendVo.getType() == null || // 赠送类型
                StringUtils.isBlank(sendVo.getGreeting())) {
            return ResponseFactory.errMissingParameter();
        }
        if (sendVo.getType() == 2 && (sendVo.getTargetTime() == null ||
                sendVo.getTargetTime().getTime() < System.currentTimeMillis() + 60000)) {
            return ResponseFactory.err("送礼时间必填并且大于2分钟");
        }
        if (StringUtils.isBlank(sendVo.getEvent())) {
            sendVo.setEvent("日常关怀");
        }
        return giftService.sendForAppV2(userDetails.getUserId(), sendVo, client);
    }



    /*----------------------------------------礼物赠送v2结束---------------------------------------------------*/


    /**
     * app赠送礼物实现
     *
     * @param userDetails 赠送者信息
     * @param sendVo      赠送礼物信息 type 1 未领取 2 已领取部分 3 已领取全部 4 超时领取失败
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @RequestMapping("sendForApp")
    public Response sendForApp(@AuthenticationPrincipal UserDetails userDetails,
                               @AppClient Integer client,
                               GiftSendVo sendVo, String s2, String de) {
        if (sendVo.getBpId() == null || // 主物品id
                sendVo.getFriendUserId() == null || // 赠送好友id
                sendVo.getType() == null || // 赠送类型
                StringUtils.isAnyBlank(sendVo.getGreeting(), sendVo.getEvent()) ||
                (sendVo.getType() == 2 && (sendVo.getTargetTime() == null ||
                        sendVo.getTargetTime().getTime() < System.currentTimeMillis() + 60000))) {
            return ResponseFactory.errMissingParameter();
        }
        return giftService.sendForApp(userDetails.getUserId(), sendVo, de, s2, client);
    }

    // 有密码校验
//    public Response sendForApp(@AuthenticationPrincipal UserDetails userDetails,
//                               @AppClient Integer client,
//                               GiftSendVo sendVo, String s2, String de) {
//        if (sendVo.getBpId() == null || // 主物品id
//                sendVo.getFriendUserId() == null || // 赠送好友id
//                sendVo.getType() == null || // 赠送类型
//                StringUtils.isAnyBlank(sendVo.getGreeting(), sendVo.getEvent()
//                        , s2, de) ||
//                (sendVo.getType() == 2 && (sendVo.getTargetTime() == null ||
//                        sendVo.getTargetTime().getTime() < System.currentTimeMillis() + 60000))) {
//            return ResponseFactory.errMissingParameter();
//        }
//        return giftService.sendForApp(userDetails.getUserId(), sendVo, de, s2, client);
//    }

    /**
     * 微信赠送礼物实现
     *
     * @param userDetails 赠送者信息
     * @param sendVo      赠送礼物信息 type 1 未领取 2 已领取部分 3 已领取全部 4 超时领取失败
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @RequestMapping("sendForWx")
    public Response sendForWx(@AuthenticationPrincipal UserDetails userDetails,
                              @AppClient Integer client,
                              GiftSendVo sendVo, String bpIds) {
        if (client == 3) {
            if (sendVo.getType() == null || sendVo.getType() < 3 || sendVo.getType() > 4
//                StringUtils.isAnyBlank(sendVo.getGreeting())
            ) {
                return ResponseFactory.errMissingParameter();
            }
            // 判断随机赠送的概率
            if (sendVo.getType() == 4 && (sendVo.getP() == null || sendVo.getP() <= 0 || sendVo.getP() >= 1)) {
                return ResponseFactory.err("请输入中奖率");
            }
            // json数组反序列化
            HashSet<SendWXVo> sendWXVos = JSON.parseObject(bpIds, new TypeReference<HashSet<SendWXVo>>() {
            });
            if (CollectionUtils.isEmpty(sendWXVos)) {
                return ResponseFactory.errMissingParameter();
            }
            for (SendWXVo sendWXVo : sendWXVos) {
                if (sendWXVo.getQuantity() == null || sendWXVo.getQuantity() < 1) {
                    return ResponseFactory.err("商品数量不能为空并且不能小于1");
                }
                if (sendWXVo.getBpId() == null) {
                    return ResponseFactory.err("");
                }
            }
            return giftService.sendForWx(userDetails.getUserId(), sendVo, client, sendWXVos);
        } else {
            if (sendVo.getBpId() == null ||
                    sendVo.getType() == null || sendVo.getType() < 3 || sendVo.getType() > 4
//                StringUtils.isAnyBlank(sendVo.getGreeting())
            ) {
                return ResponseFactory.errMissingParameter();
            }
            // 判断随机赠送的概率
            if (sendVo.getType() == 4 && (sendVo.getP() == null || sendVo.getP() <= 0 || sendVo.getP() >= 1)) {
                return ResponseFactory.err("请输入中奖率");
            }
            return giftService.sendForWxApp(userDetails.getUserId(), sendVo, client);
        }

    }


//    public Response sendForWx(@AuthenticationPrincipal UserDetails userDetails,
//                              @AppClient Integer client,
//                              GiftSendVo sendVo) {
//        if (sendVo.getBpId() == null ||
//                sendVo.getType() == null || sendVo.getType() < 3 || sendVo.getType() > 4
////                StringUtils.isAnyBlank(sendVo.getGreeting())
//        ) {
//            return ResponseFactory.errMissingParameter();
//        }
//        // 判断随机赠送的概率
//        if (sendVo.getType() == 4 && (sendVo.getP() == null || sendVo.getP() <= 0 || sendVo.getP() >= 1)) {
//            return ResponseFactory.err("请输入中奖率");
//        }
//        return giftService.sendForWx(userDetails.getUserId(), sendVo, client);
//    }


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


    /**
     * 小程序领取好友分享的礼物
     *
     * @param userDetails  赠送者信息
     * @param giftRecordId 礼物赠送记录id
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @RequestMapping("getGift")
    public Response wxGetGift(@AuthenticationPrincipal UserDetails userDetails,
                              Integer giftRecordId) {
        if (giftRecordId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return giftService.wxGetGift(userDetails.getUserId(), giftRecordId);
    }

    /**
     * 查看我是否领取了礼物
     *
     * @param userDetails
     * @param giftRecordId
     * @return
     */
    @RequestMapping("getGiftStatus")
    public Response wxGetGiftStatus(@AuthenticationPrincipal UserDetails userDetails,
                              Integer giftRecordId) {
        if (giftRecordId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return giftService.wxGetGiftStatus(userDetails.getUserId(), giftRecordId);
    }

}

