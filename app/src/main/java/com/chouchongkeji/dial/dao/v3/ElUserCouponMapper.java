package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.ElUserCoupon;
import com.chouchongkeji.service.v3.vo.ElCouponVo;

import java.util.List;

public interface ElUserCouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ElUserCoupon record);

    int insertSelective(ElUserCoupon record);

    ElUserCoupon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ElUserCoupon record);

    int updateByPrimaryKey(ElUserCoupon record);

    List<ElCouponVo> selectByUserId(Integer userId);
}
