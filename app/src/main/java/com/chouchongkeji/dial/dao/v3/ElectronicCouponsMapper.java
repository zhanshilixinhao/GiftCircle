package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.ElectronicCoupons;

public interface ElectronicCouponsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ElectronicCoupons record);

    int insertSelective(ElectronicCoupons record);

    ElectronicCoupons selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ElectronicCoupons record);

    int updateByPrimaryKey(ElectronicCoupons record);

    ElectronicCoupons selectByNum(Long num);
}
