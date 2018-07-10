package com.chouchongkeji.dial.dao.iwant.wallet;

import com.chouchongkeji.dial.pojo.iwant.wallet.WalletRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WalletRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WalletRecord record);

    int insertSelective(WalletRecord record);

    WalletRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WalletRecord record);

    int updateByPrimaryKey(WalletRecord record);

    List<WalletRecord> selectByUserId(@Param("userId") Integer userId,
                                      @Param("starting") Long starting,
                                      @Param("ending") Long ending);

}