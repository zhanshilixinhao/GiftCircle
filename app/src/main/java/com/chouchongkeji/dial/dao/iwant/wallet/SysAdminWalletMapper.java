package com.chouchongkeji.dial.dao.iwant.wallet;

import com.chouchongkeji.dial.pojo.iwant.wallet.SysAdminWallet;

public interface SysAdminWalletMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAdminWallet record);

    int insertSelective(SysAdminWallet record);

    SysAdminWallet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAdminWallet record);

    int updateByPrimaryKey(SysAdminWallet record);

    /**
     * 根据后台用户查询
     * @param adminId
     * @return
     */
    SysAdminWallet selectByAdminId(Integer adminId);
}