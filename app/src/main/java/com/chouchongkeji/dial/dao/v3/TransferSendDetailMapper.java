package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.TransferSendDetail;

public interface TransferSendDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TransferSendDetail record);

    int insertSelective(TransferSendDetail record);

    TransferSendDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TransferSendDetail record);

    int updateByPrimaryKey(TransferSendDetail record);
}