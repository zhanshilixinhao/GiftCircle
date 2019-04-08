package com.chouchongkeji.dial.dao.iwant.wallet;

import com.chouchongkeji.dial.pojo.iwant.wallet.SysAdminWithdraw;

public interface SysAdminWithdrawMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAdminWithdraw record);

    int insertSelective(SysAdminWithdraw record);

    SysAdminWithdraw selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAdminWithdraw record);

    int updateByPrimaryKey(SysAdminWithdraw record);
}