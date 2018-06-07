package com.chouchongkeji.dao.iwant.wallet;

import com.chouchongkeji.pojo.iwant.wallet.Wallet;

public interface WalletMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    Wallet selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKey(Wallet record);

    Wallet selectByUserId(Integer userId);

}