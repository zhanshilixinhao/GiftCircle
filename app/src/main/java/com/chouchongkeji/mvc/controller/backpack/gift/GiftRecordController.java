package com.chouchongkeji.mvc.controller.backpack.gift;

import com.chouchongkeji.dial.pojo.home.GiftRecordSelf;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.gift.GiftRecordService;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
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
        return giftRecordService.getInOut(userDetails.getUserId());
    }

    /**
     * 获取人情账簿列表
     *
     * @param userDetails 用户信息
     * @param starting    开始日期
     * @param ending      结束日期
     * @param obType      类型 个人 | 家庭
     * @return
     * @author yichenshanren
     * @date 2018/7/9
     */
    @PostMapping("list")
    public Response getList(@AuthenticationPrincipal UserDetails userDetails,
                            Long starting, Long ending, String obType, PageQuery page) {
        if (starting != null) {
            starting = starting / 1000;
        }
        if (ending != null) {
            ending = ending / 1000;
        }
        return giftRecordService.getList(userDetails.getUserId(), starting, ending, obType, page);
    }

    /**
     * 礼品补录
     *
     * @param userDetails 用户信息
     * @param record      礼物记录
     * @return
     * @author yichenshanren
     * @date 2018/7/9
     */
    @PostMapping("add")
    public Response addRecord(@AuthenticationPrincipal UserDetails userDetails,
                              GiftRecordSelf record) {
        if (StringUtils.isAnyBlank(record.getEvent(), record.getDetail(), record.getObType()) ||
                record.getAmount() == null || record.getAmount().doubleValue() <= 0 ||
                record.getInoutType() == null || record.getInoutType() < 1 || record.getInoutType() > 2 ||
                record.getTargetTime() == null) {
            return ResponseFactory.errMissingParameter();
        }
        return giftRecordService.addRecord(userDetails.getUserId(), record);
    }

    /**
     * 人情往来记录
     *
     * @param userDetails  用户信息
     * @param friendUserId 查看的好友用户id
     * @return
     * @author yichenshanren
     * @date 2018/7/9
     */
    @PostMapping("friend")
    public Response getRecordForFriend(@AuthenticationPrincipal UserDetails userDetails,
                              Integer friendUserId, PageQuery page) {
        if (friendUserId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return giftRecordService.getRecordForFriend(userDetails.getUserId(), friendUserId, page);
    }
}
