package com.chouchongkeji.dao.user;

import com.chouchongkeji.pojo.user.PaymentInfo;

public interface PaymentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PaymentInfo record);

    int insertSelective(PaymentInfo record);

    PaymentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PaymentInfo record);

    int updateByPrimaryKey(PaymentInfo record);

    int checkPaymentInfo(Long orderNo);

}