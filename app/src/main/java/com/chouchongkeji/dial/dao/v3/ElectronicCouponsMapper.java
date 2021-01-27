package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.ElectronicCoupons;
import com.chouchongkeji.service.v4.vo.CoVo;

public interface ElectronicCouponsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ElectronicCoupons record);

    int insertSelective(ElectronicCoupons record);

    ElectronicCoupons selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ElectronicCoupons record);

    int updateByPrimaryKey(ElectronicCoupons record);

    ElectronicCoupons selectByNum(Long num);

    CoVo findByNum(Long num);
}
