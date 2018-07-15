package com.chouchongkeji.mvc.controller.backpack.gift;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.goexplore.utils.Utils;
import com.chouchongkeji.service.backpack.gift.GiftExchangeService;
import com.chouchongkeji.util.Constants;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

/**
 * @author linqin
 * @date 2018/7/13
 */
@RestController
@RequestMapping("auth/v1/exchange")
public class GiftExchangeController {

    @Autowired
    private GiftExchangeService giftExchangeService;

    /**
     * 用户添加想要交换的物品到交换记录
     *
     * @param userDetails
     * @param friendUserId 好友Id
     * @param exchangeBpId 想交换的礼物 背包Id用逗号隔开
     * @param wantBpId     想要的礼物  背包Id用逗号隔开
     * @return
     * @author linqin
     * @date 2018/7/13
     */
    @PostMapping("add")
    public Response addItem(@AuthenticationPrincipal UserDetails userDetails, Integer friendUserId, String exchangeBpId,
                            String wantBpId) {
        //校验参数
        if (friendUserId == null) {
            return ResponseFactory.errMissingParameter();
        }
        //想交换的礼物
        if (exchangeBpId == null) {
            return ResponseFactory.errMissingParameter();
        }

        HashSet<Long> ids = new HashSet<>();
        boolean ids1 = Utils.getIdsForLong(exchangeBpId, ids);//把,隔开的字符串转换为集合
        if (ids1) return ResponseFactory.err("id错误");
        //想要的礼物
        if (wantBpId == null) {
            return ResponseFactory.errMissingParameter();
        }
        HashSet<Long> id = new HashSet<>();
        boolean id1 = Utils.getIdsForLong(wantBpId, id);//把,隔开的字符串转换为集合
        if (id1) return ResponseFactory.err("id1错误");
        return giftExchangeService.addItem(userDetails.getUserId(), friendUserId, ids, id);
    }

    /**
     * 好友提交要交换的物品
     *
     * @param userDetails
     * @param giftExchangeId 物品交换记录Id
     * @param submitId       提交的物品Id 背包Id用逗号隔开
     * @return
     * @author linqin
     * @date 2018/7/13
     */
    @PostMapping("friend_add")
    public Response friendAddItem(@AuthenticationPrincipal UserDetails userDetails, Integer giftExchangeId, String submitId) {
        //参数校验
        if (giftExchangeId == null) {
            return ResponseFactory.errMissingParameter();
        }
        //把逗号隔开的字符串转化为集合
        HashSet<Long> id = new HashSet<>();
        boolean ids = Utils.getIdsForLong(submitId, id);
        if (ids) return ResponseFactory.err("ids错误");
        return giftExchangeService.friendAddItem(userDetails.getUserId(), giftExchangeId, id);
    }


    /**
     * 确认交换礼物
     *
     * @param userDetails
     * @param giftExchangeId 礼物交换记录id
     * @param operation      操作 0-默认，1-确认交换，2-取消交换
     * @return
     * @author linqin
     * @date 2018/7/14
     */
    @PostMapping("confirm")
    public Response confirm(@AuthenticationPrincipal UserDetails userDetails, Integer giftExchangeId, Byte operation) {
        //参数校验
        if (giftExchangeId == null) {
            return ResponseFactory.errMissingParameter();
        }
        if (operation == null) {
            operation = Constants.BP_ITEM.DEFAULT;
        }
        if (operation != Constants.BP_ITEM.DEFAULT && operation != Constants.BP_ITEM.AGREE && operation != Constants.BP_ITEM.REFUSE) {
            return ResponseFactory.errMissingParameter();
        }
        return giftExchangeService.confirm(userDetails.getUserId(), giftExchangeId, operation);
    }


    /**
     * 交换记录列表
     *
     * @param userDetails
     * @param status      状态，0-全部，1-交易中，2-已完成，
     * @return
     * @author linqin
     * @date 2018/7/14
     */
    @PostMapping("list")
    public Response getList(@AuthenticationPrincipal UserDetails userDetails, Byte status, PageQuery pageQuery) {
        //校验参数
        if (status == null) {
            status = 0;
        }
        if (status != 0 && status != 1 && status != 2) {
            return ResponseFactory.errMissingParameter();
        }
        return giftExchangeService.getList(userDetails.getUserId(),status,pageQuery);
    }


    /**
     * 礼物交换详情
     * @param userDetails
     * @param giftExchangeId 礼物交换记录id
     * @return
     * @author linqin
     * @date 2018/7/15
     */
    @PostMapping("detail")
    public Response giftExDetail(@AuthenticationPrincipal UserDetails userDetails, Integer giftExchangeId){
        if (giftExchangeId ==null){
            return ResponseFactory.errMissingParameter();
        }
        return giftExchangeService.giftExDetail(userDetails.getUserId(),giftExchangeId);
    }


}
