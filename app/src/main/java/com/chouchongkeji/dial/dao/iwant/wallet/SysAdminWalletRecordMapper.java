package com.chouchongkeji.dial.dao.iwant.wallet;

import com.chouchongkeji.dial.pojo.iwant.wallet.SysAdminWalletRecord;

public interface SysAdminWalletRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAdminWalletRecord record);

    int insertSelective(SysAdminWalletRecord record);

    SysAdminWalletRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAdminWalletRecord record);

    int updateByPrimaryKey(SysAdminWalletRecord record);
}