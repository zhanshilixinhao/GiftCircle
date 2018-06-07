package com.chouchongkeji.service.iwant.wallet.impl;

import com.chouchongkeji.dao.iwant.wallet.WalletMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.pojo.iwant.wallet.Wallet;
import com.chouchongkeji.service.iwant.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author yy
 * @date 2018/6/5
 **/

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletMapper walletMapper;


    /**
     * 获取钱包详情
     * @param userId 用户Id
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @Override
    public Response getWalletDetail(Integer userId) {
        //校验必传参数
        if (userId == null){
            return ResponseFactory.errMissingParameter();
        }
        //根用户Id查询钱包信息
        Wallet detail = walletMapper.selectByUserId(userId);
        if (detail != null){
            return ResponseFactory.sucData(detail);
        }
        //如果钱包信息为空，插入一条数据
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setBalance(BigDecimal.valueOf(0));
        wallet.setTotalAmount(BigDecimal.valueOf(0));
        wallet.setConsumeAmount(BigDecimal.valueOf(0));
        walletMapper.insert(wallet);
        return ResponseFactory.sucData(wallet);
    }


}
