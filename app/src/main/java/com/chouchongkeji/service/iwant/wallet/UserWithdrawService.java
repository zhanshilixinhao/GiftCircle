package com.chouchongkeji.service.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;

import java.math.BigDecimal;

public interface UserWithdrawService {
    /**
     * 添加用户提现记录
     *
     * @param: [userId 用户id, id 用户银行卡id, amount 提现金额]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/8
     */
    Response addUserWithdraw(Integer userId, Integer id, BigDecimal amount);
}
