package com.chouchongkeji.service.backpack.base;

import com.chouchongkeji.dial.pojo.backpack.BpItem;
import com.chouchongkeji.dial.pojo.backpack.ForRecord;
import com.chouchongkeji.dial.pojo.backpack.consignment.ConsignmentOrder;
import com.chouchongkeji.dial.pojo.gift.item.ItemOrderDetail;
import com.chouchongkeji.dial.pojo.gift.virtualItem.VirItemOrder;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.gift.vo.GiftExItemVo;
import com.chouchongkeji.service.backpack.gift.vo.GiftItemVo;

import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/7/2
 */

public interface BpService {

    /**
     * 背包列表
     *
     * @param userId 用户信息
     * @param type   1 物品 2 虚拟物品 3 优惠券
     * @param page
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    Response getList(Integer userId, Integer type, PageQuery page);

    /**
     * 添加到背包
     *
     * @param items 添加的物品
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    int addBatch(List<BpItem> items);


    /**
     * 商品购买成功添加到背包
     *
     * @param details 购买的商品列表
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    int addFromItemOrder(List<ItemOrderDetail> details);

    /**
     * 虚拟商品购买成功添加到背包
     *
     * @param virItemOrder 购买虚拟商品的订单
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    int addFromVirtualItemOrder(VirItemOrder virItemOrder);

    /**
     * 寄售台物品购买成功添加到背包
     *
     * @param consignmentOrder 购买寄售台商品的订单
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    int addFromConsignmengOrder(ConsignmentOrder consignmentOrder);

    /**
     * 赠送的物品添加到背包
     *
     * @param detail 赠送的礼物信息
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    int addFromGiftSent(Integer recordDetailId, Integer userId, List<GiftItemVo> vos);

    /**
     * 背包列表
     *
     * @param userId 用户信息
     * @param key         关键字
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    Response search(Integer userId, String key, PageQuery page);

    /**
     * 向好友索要物品成功添加到背包
     * @param forRecord
     * @param bpItem
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    int addFromFriendBp(ForRecord forRecord, BpItem bpItem);

    /**
     * 礼物互换添加到背包
     * @param giftExchangeId
     * @param userId
     * @param vos
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    int addFromGiftExchange(Integer giftExchangeId, Integer userId, List<GiftExItemVo> vos);

    /**
     * 添加一条记录
     *
     * @param item 背包信息
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    int add(BpItem item);

    /**
     * 我的背包红点(未查看的新品)
     *
     * @param userId
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    Response getUnreadBpItem(Integer userId);

    /**
     * 微信背包列表
     *
     * @param userDetails 用户信息
     * @param type        1 物品 2 虚拟物品 3 优惠券
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    Response getWxBpList(Integer userId, Integer type);
}
