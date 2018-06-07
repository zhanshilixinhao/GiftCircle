package com.chouchongkeji.service.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2018/6/6
 */
public interface UserChargeService {
    /**
     * 创建充值订单
     * @param userId 用户id
     * @param amount 充值金额
     * @return
     * @author linqin
     * @date 2018/6/6
     */
    Response chargeOrder(Integer userId,Integer client,BigDecimal amount,Integer payWay);
}
