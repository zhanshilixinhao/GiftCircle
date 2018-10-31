package com.chouchongkeji.service.backpack.gift;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.backpack.gift.vo.GiftBaseVo;
import com.chouchongkeji.service.backpack.gift.vo.GiftSendVo;

import java.util.Date;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/7/2
 */

public interface GiftService {

    /**
     * app赠送礼物实现
     *
     * @param userId 赠送者信息
     * @param sendVo 赠送礼物信息
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    Response sendForApp(Integer userId, GiftSendVo sendVo, String de, String s2, Integer client);

    /**
     * 答谢礼物赠送
     *
     * @param userId         礼物接收者id
     * @param recordDetailId 记录详情id
     * @param reply          回复内容
     * @return
     * @author yichenshanren
     * @date 2018/7/21
     */
    Response acknowledge(Integer userId, Integer recordDetailId, String reply);


    /**
     * 获取started之后收到的礼物记录
     *
     * @param userId  用户id
     * @param started 开始日期
     * @author yichenshanren
     * @date 2018/7/2
     */
    List<GiftBaseVo> getWithDays(Integer userId, Date started);

    /**
     * app赠送礼物实现
     *
     * @param userId 赠送者信息
     * @param sendVo 赠送礼物信息 type 1 未领取 2 已领取部分 3 已领取全部 4 超时领取失败
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    Response sendForWx(Integer userId, GiftSendVo sendVo, Integer client);

    /**
     * 小程序领取好友分享的礼物
     *
     * @param userId       赠送者信息
     * @param giftRecordId 礼物赠送记录id
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    Response wxGetGift(Integer userId, Integer giftRecordId);
}
