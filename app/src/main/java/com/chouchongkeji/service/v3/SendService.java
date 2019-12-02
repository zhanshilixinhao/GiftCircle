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
     * @param userDetails 用户(赠送者)
     * @param cardId 卡片id
     * @param sendMoney 赠送金额
     * @return
     */
    Response cardSend(UserDetails userDetails, Integer cardId, BigDecimal sendMoney);
}
