package com.chouchongkeji.dao.iwant.wallet;

import com.chouchongkeji.pojo.iwant.wallet.UserWithdraw;

public interface UserWithdrawMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserWithdraw record);

    int insertSelective(UserWithdraw record);

    UserWithdraw selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserWithdraw record);

    int updateByPrimaryKey(UserWithdraw record);
}