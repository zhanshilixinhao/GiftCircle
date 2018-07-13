package com.chouchongkeji.service.backpack.base;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

/**
 * @author linqin
 * @date 2018/7/12
 */
public interface FriendBpService {

    /**
     * 好友背包列表
     *
     * @param userId       用户id
     * @param friendUserId 好友用户Id
     * @param type         1 物品 2 虚拟物品 3 优惠券
     * @return
     * @author linqin
     * @date 2018/7/12
     */
    Response getList(Integer userId, Integer friendUserId, Integer type, PageQuery pageQuery);

    /**
     * 好友背包物品详情
     *
     * @param userId       用户Id
     * @param friendUserId 好友用户Id
     * @param bpId         背包id
     * @return
     * @author linqin
     * @date 2018/7/12
     */
    Response itemDetail(Integer userId, Integer friendUserId, Long bpId);

    /**
     * 好友背包物品添加到索要记录
     *
     * @param userId       用户id
     * @param friendUserId 好友用户Id
     * @param bpId         背包id
     * @return
     * @author linqin
     * @date 2018/7/12
     */
    Response addForRecord(Integer userId, Integer friendUserId, Long bpId);

    /**
     * 索要记录列表
     * @param userId
     * @return
     * @author linqin
     * @date 2018/7/12
     */
    Response getRecordList(Integer userId, PageQuery pageQuery);

    /**
     * 同意或者拒绝好友索要背包物品
     *
     * @param userId
     * @param operation   0-默认无操作，1-同意好友索要，2-拒绝好友索要
     * @param forRecordId   索要记录id
     * @return
     * @author linqin
     * @date 2018/7/13
     */
    Response operation(Integer userId, Byte operation,Integer forRecordId);
}
