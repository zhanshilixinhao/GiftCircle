package com.chouchongkeji.service.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.util.Constants;

import java.math.BigDecimal;

/**
 * @author yy
 * @date 2018/6/5
 **/
public interface WalletService {
    /**
     * 获取钱包详情
     * @param userId 用户Id
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    Response getWalletDetail(Integer userId);

    /**
     * 更新余额
     * @param userId
     * @param amount
     * @author linqin
     * @date 2018/6/7
     */
    void updateBalance(Integer userId, BigDecimal amount);

    int updateBalance(Integer userId, BigDecimal amount, Constants.WALLET_RECORD type, Integer targetId);
}
