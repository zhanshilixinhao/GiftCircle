package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.ElCouponSend;

import java.util.List;

public interface ElCouponSendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ElCouponSend record);

    int insertSelective(ElCouponSend record);

    ElCouponSend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ElCouponSend record);

    int updateByPrimaryKey(ElCouponSend record);

    List<ElCouponSend> selectByAll();

}
