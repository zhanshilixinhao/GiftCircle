package com.chouchongkeji.service.backpack.gift;

import com.chouchongkeji.goexplore.common.Response;

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
    Response sendList(Integer userId, Byte flag);
}
