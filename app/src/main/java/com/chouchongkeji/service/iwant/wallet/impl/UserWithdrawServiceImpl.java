package com.chouchongkeji.service.iwant.wallet.impl;

import com.chouchongkeji.dao.iwant.wallet.UserWithdrawMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.iwant.wallet.UserWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author yy
 * @date 2018/6/5
 **/

@Service
public class UserWithdrawServiceImpl implements UserWithdrawService{
    @Autowired
    private UserWithdrawMapper userWithdrawMapper;

    /**
     * 添加用户提现记录
     *
     * @param: [userId 用户id, id 用户银行卡id, amount 提现金额]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/8
     */
    @Override
    public Response addUserWithdraw(Integer userId, Integer id, BigDecimal amount) {

        return null;
    }
}
