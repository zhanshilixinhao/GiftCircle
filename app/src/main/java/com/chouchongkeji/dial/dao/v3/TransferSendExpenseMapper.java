package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.TransferSendExpense;

public interface TransferSendExpenseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TransferSendExpense record);

    int insertSelective(TransferSendExpense record);

    TransferSendExpense selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TransferSendExpense record);

    int updateByPrimaryKey(TransferSendExpense record);
}