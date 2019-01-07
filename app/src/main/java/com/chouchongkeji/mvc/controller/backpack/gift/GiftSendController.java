package com.chouchongkeji.mvc.controller.backpack.gift;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.goexplore.utils.Utils;
import com.chouchongkeji.service.backpack.gift.GiftSendService;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

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
     * 赠送礼物列表/收礼记录列表
     *
     * @param userDetails
     * @param flag        1 赠送记录 2 收礼记录
     * @return
     * @author linqin
     * @date 2019/1/4 11:46
     */
    @PostMapping("send_list")
    public Response sendList(@AuthenticationPrincipal UserDetails userDetails, Byte flag, PageQuery pageQuery) {
        if (flag == null || (flag != 1 && flag != 2)) {
            return ResponseFactory.errMissingParameter();
        }
        return giftSendService.sendList(userDetails.getUserId(), flag,pageQuery);
    }

    /**
     * 取消礼物赠送
     *
     * @param userDetails
     * @param recordId 礼物记录id
     * @return
     * @author linqin
     * * @date 2019/1/4 11:46
     */
    @PostMapping("cancel_send")
    public Response cancelSend(@AuthenticationPrincipal UserDetails userDetails, Integer recordId) {
        if (recordId == null){
            return ResponseFactory.errMissingParameter();
        }
        return giftSendService.cancelSend(userDetails.getUserId(),recordId);
    }


    /**
     * 删除礼物赠送记录
     * @param userDetails
     * @param recordId 礼物记录id 多个id时用逗号隔开
     * @return
     * @author linqin
     * @date 2019/1/4 11:46
     */
    @PostMapping("delete_send")
    public Response deleteSendRecord(@AuthenticationPrincipal UserDetails userDetails,String recordId){
        if (StringUtils.isBlank(recordId)){
            return ResponseFactory.errMissingParameter();
        }
        HashSet<Integer> ids = new HashSet<>();
        boolean ids1 = Utils.getIds(recordId,ids);
        if (ids1) return ResponseFactory.err("id错误");
        return giftSendService.deleteSendRecord(userDetails.getUserId(),ids);
    }

    /**
     * 删除收礼记录
     * @param userDetails
     * @param recordDetailId 礼物记录详情id 多个id时用逗号隔开
     * @return
     * @author linqin
     * @date 2019/1/4 11:46
     */
    @PostMapping("delete_receive")
    public Response deleteReceiveRecord(@AuthenticationPrincipal UserDetails userDetails,String recordDetailId){
        if (StringUtils.isBlank(recordDetailId)){
            return ResponseFactory.errMissingParameter();
        }
        HashSet<Integer> ids = new HashSet<>();
        boolean ids1 = Utils.getIds(recordDetailId,ids);
        if (ids1) return ResponseFactory.err("id错误");
        return giftSendService.deleteReceiveRecord(userDetails.getUserId(),ids);
    }




}
