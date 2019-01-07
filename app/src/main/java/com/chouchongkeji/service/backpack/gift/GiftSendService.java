package com.chouchongkeji.service.backpack.gift;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

import java.util.HashSet;

/**
 * @author linqin
 * @date 2019/1/4 11:49
 */

public interface GiftSendService {

    /**
     * 赠送礼物列表
     *
     * @param userId *
     * @param flag 1 赠送记录 2 收礼记录
     * @return
     * @author linqin
     * @date 2019/1/4 11:46
     */
    Response sendList(Integer userId, Byte flag, PageQuery pageQuery);

    /**
     * 取消礼物赠送
     *
     * @param userId
     * @param recordId 礼物记录id
     * @return
     * @author linqin
     * * @date 2019/1/4 11:46
     */
    Response cancelSend(Integer userId, Integer recordId);

    /**
     * 删除礼物赠送记录
     * @param userId
     * @param ids
     * @return
     * @author linqin
     * @date 2019/1/4 11:46
     */
    Response deleteSendRecord(Integer userId, HashSet<Integer> ids);

    /**
     * 删除收礼记录
     * @param userId
     * @param ids 礼物记录详情id 多个id时用逗号隔开
     * @return
     * @author linqin
     * @date 2019/1/4 11:46
     */
    Response deleteReceiveRecord(Integer userId, HashSet<Integer> ids);
}
