package com.chouchongkeji.service.backpack.gift;

import com.chouchongkeji.dial.pojo.home.GiftRecordSelf;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

/**
 * @author yichenshanren
 * @date 2018/7/9
 */

public interface GiftRecordService {

    /**
     * 获取一个用户的总的收支
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/7/9
     */
    Response getInOut(Integer userId);

    /**
     * 获取人情账簿列表
     *
     * @param userId   用户信息
     * @param starting 开始日期
     * @param ending   结束日期
     * @param obType   类型 个人 | 家庭
     * @param page
     * @return
     * @author yichenshanren
     * @date 2018/7/9
     */
    Response getList(Integer userId, Long starting, Long ending, String obType, PageQuery page);


    /**
     * 礼品补录
     *
     * @param userId 用户信息
     * @param record 礼物记录
     * @return
     * @author yichenshanren
     * @date 2018/7/9
     */
    Response addRecord(Integer userId, GiftRecordSelf record);

    /**
     * 人情往来记录
     *
     * @param userId       用户信息
     * @param friendUserId 查看的好友用户id
     * @return
     * @author yichenshanren
     * @date 2018/7/9
     */
    Response getRecordForFriend(Integer userId, Integer friendUserId, PageQuery page);
}
