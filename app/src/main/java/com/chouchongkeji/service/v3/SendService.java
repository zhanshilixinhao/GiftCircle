package com.chouchongkeji.service.v3;

import com.chouchongkeji.goexplore.common.Response;
import com.yichen.auth.service.UserDetails;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2019/12/2
 */

public interface SendService {


    /**
     * 会员卡余额赠送
     * @param userId 用户(赠送者)
     * @param cardId 卡片id
     * @param sendMoney 赠送金额
     * @return
     */
    Response cardSend(Integer userId, Integer cardId, BigDecimal sendMoney);

    /**
     * 微信领取会员卡
     * @param userId 用户（接收者）
     * @param transferSendId 转赠记录id
     * @return
     */
    Response getCardSend(Integer userId, Integer transferSendId);

    /**
     * 判断是否可以领取会员卡
     * @param userId 用户（接收者）
     * @param transferSendId 转赠记录id
     * @return
     */
    Response judgeCardSend(Integer userId, Integer transferSendId);
}
