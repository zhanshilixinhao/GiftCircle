package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.TransferSend;

public interface TransferSendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TransferSend record);

    int insertSelective(TransferSend record);

    TransferSend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TransferSend record);

    int updateByPrimaryKey(TransferSend record);
}