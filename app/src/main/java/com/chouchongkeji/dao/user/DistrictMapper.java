package com.chouchongkeji.dao.user;

import com.chouchongkeji.pojo.user.District;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer adcode);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Integer adcode);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
}