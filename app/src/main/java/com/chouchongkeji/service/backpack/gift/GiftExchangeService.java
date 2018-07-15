package com.chouchongkeji.service.backpack.gift;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

import java.util.HashSet;

/**
 * @author linqin
 * @date 2018/7/13
 */
public interface GiftExchangeService {

    /**
     * 用户添加想要交换的物品到交换记录
     * @param userId
     * @param friendUserId 好友Id
     * @param exchangeBpId 想交换的礼物 背包Id用逗号隔开
     * @param wantBpId  想要的礼物  背包Id用逗号隔开
     * @return
     * @author linqin
     * @date 2018/7/13
     */
    Response addItem(Integer userId, Integer friendUserId, HashSet<Long> exchangeBpId, HashSet<Long> wantBpId);

    /**
     * 好友提交要交换的物品
     * @param userId
     * @param giftExchangeId 物品交换记录Id
     * @param submitId 提交的物品Id 背包Id用逗号隔开
     * @return
     * @author linqin
     * @date 2018/7/13
     */
    Response friendAddItem(Integer userId, Integer giftExchangeId, HashSet<Long> submitId);


    /**
     * 确认交换礼物
     * @param userId
     * @param giftExchangeId 礼物交换记录id
     * @param operation 操作 0-默认，1-确认交换，2-取消交换
     * @return
     * @author linqin
     * @date 2018/7/14
     */
    Response confirm(Integer userId, Integer giftExchangeId, Byte operation);


    /**
     * 交换记录列表
     *
     * @param userId
     * @param status      状态，0-全部，1-交易中，2-已完成，
     * @return
     * @author linqin
     * @date 2018/7/14
     */
    Response getList(Integer userId, Byte status, PageQuery pageQuery);

    /**
     * 礼物交换详情
     * @param userId
     * @param giftExchangeId 礼物交换记录id
     * @return
     * @author linqin
     * @date 2018/7/15
     */
    Response giftExDetail(Integer userId, Integer giftExchangeId);
}
