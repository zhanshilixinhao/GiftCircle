package com.chouchongkeji.dial.dao.iwant.wallet;

import com.chouchongkeji.dial.pojo.iwant.wallet.FireworksRecord;

public interface FireworksRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FireworksRecord record);

    int insertSelective(FireworksRecord record);

    FireworksRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FireworksRecord record);

    int updateByPrimaryKey(FireworksRecord record);
}