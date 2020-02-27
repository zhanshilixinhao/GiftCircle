package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.TransferWithhold;

public interface TransferWithholdMapper {
    int deleteByPrimaryKey(Integer transferSendId);

    int insert(TransferWithhold record);

    int insertSelective(TransferWithhold record);

    TransferWithhold selectByPrimaryKey(Integer transferSendId);

    int updateByPrimaryKeySelective(TransferWithhold record);

    int updateByPrimaryKey(TransferWithhold record);
}