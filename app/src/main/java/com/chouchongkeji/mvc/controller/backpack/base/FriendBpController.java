package com.chouchongkeji.mvc.controller.backpack.base;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.base.FriendBpService;
import com.chouchongkeji.util.Constants;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2018/7/12
 */
@RestController
@RequestMapping("auth/friend/bp")
public class FriendBpController {

    @Autowired
    private FriendBpService friendBpService;

    /**
     * 好友背包列表
     *
     * @param userDetails
     * @param friendUserId 好友用户Id
     * @param type         1 物品 2 虚拟物品 3 优惠券
     * @return
     * @author linqin
     * @date 2018/7/12
     */
    @PostMapping("list")
    public Response getList(@AuthenticationPrincipal UserDetails userDetails, Integer friendUserId, Integer type,
                            PageQuery pageQuery) {
        if (friendUserId == null) {
            return ResponseFactory.errMissingParameter();
        }
        if (type == null || type < 0 || type > 3) {
            return ResponseFactory.err("type错误!");
        }
        return friendBpService.getList(userDetails.getUserId(), friendUserId, type, pageQuery);
    }

    /**
     * 好友背包物品详情
     *
     * @param userDetails
     * @param friendUserId 好友用户Id
     * @param bpId         背包id
     * @return
     * @author linqin
     * @date 2018/7/12
     */
    @PostMapping("detail")
    public Response itemDetail(@AuthenticationPrincipal UserDetails userDetails, Integer friendUserId, Long bpId) {
        if (friendUserId == null) {
            return ResponseFactory.errMissingParameter();
        }
        if (bpId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return friendBpService.itemDetail(userDetails.getUserId(), friendUserId, bpId);
    }

    /**
     * 好友背包物品添加到索要记录
     *
     * @param userDetails
     * @param friendUserId 好友用户Id
     * @param bpId         背包id
     * @return
     * @author linqin
     * @date 2018/7/12
     */
    @PostMapping("add_for_record")
    public Response addForRecord(@AuthenticationPrincipal UserDetails userDetails, Integer friendUserId, Long bpId) {
        if (friendUserId == null) {
            return ResponseFactory.errMissingParameter();
        }
        if (bpId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return friendBpService.addForRecord(userDetails.getUserId(), friendUserId, bpId);
    }


    /**
     * 索要记录列表
     *
     * @param userDetails
     * @param type        1-用户向好友索要商品 ，2-好友向用户索要商品
     * @return
     * @author linqin
     * @date 2018/7/12
     */
    @PostMapping("record_list")
    public Response getRecordList(@AuthenticationPrincipal UserDetails userDetails, PageQuery pageQuery, Integer type) {
        if (type == null || (type != 1 && type != 2)) {
            return ResponseFactory.errMissingParameter();
        }
        return friendBpService.getRecordList(userDetails.getUserId(), pageQuery, type);
    }

    /**
     * 删除索要记录
     *
     * @param userDetails
     * @param recordId    索要记录id
     * @return
     * @author linqin
     * @date 2018/7/12
     */
    @PostMapping("delete_record")
    public Response deleteRecord(@AuthenticationPrincipal UserDetails userDetails, Integer recordId) {
        if (recordId == null){
            return ResponseFactory.errMissingParameter();
        }
        return friendBpService.deleteRecord(userDetails.getUserId(),recordId);
    }

    /**
     * 同意或者拒绝好友索要背包物品
     *
     * @param userDetails
     * @param operation   0-默认无操作，1-同意好友索要，2-拒绝好友索要
     * @param forRecordId 索要记录Id
     * @return
     * @author linqin
     * @date 2018/7/13
     */
    @PostMapping("operation")
    public Response operation(@AuthenticationPrincipal UserDetails userDetails, Byte operation, Integer forRecordId) {
        //参数校验
        if (operation == null) {
            operation = Constants.BP_ITEM.DEFAULT;
        }
        if (operation != Constants.BP_ITEM.DEFAULT && operation != Constants.BP_ITEM.AGREE && operation != Constants.BP_ITEM.REFUSE) {
            return ResponseFactory.errMissingParameter();
        }
        if (forRecordId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return friendBpService.operation(userDetails.getUserId(), operation, forRecordId);
    }


}
