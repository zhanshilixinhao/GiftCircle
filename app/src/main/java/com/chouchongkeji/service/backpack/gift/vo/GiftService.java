package com.chouchongkeji.service.backpack.gift.vo;

import com.chouchongkeji.goexplore.common.Response;

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
    Response sendForApp(Integer userId, GiftSendVo sendVo);
}
