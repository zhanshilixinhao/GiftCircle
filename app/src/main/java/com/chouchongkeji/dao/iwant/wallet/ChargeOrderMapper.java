package com.chouchongkeji.dao.iwant.wallet;

import com.chouchongkeji.pojo.iwant.wallet.ChargeOrder;

public interface ChargeOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChargeOrder record);

    int insertSelective(ChargeOrder record);

    ChargeOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChargeOrder record);

    int updateByPrimaryKey(ChargeOrder record);

    ChargeOrder selectByOrderNo(Long orderNo);


}