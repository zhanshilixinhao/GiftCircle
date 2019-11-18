package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.StoreTurnover;

public interface StoreTurnoverMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreTurnover record);

    int insertSelective(StoreTurnover record);

    StoreTurnover selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreTurnover record);

    int updateByPrimaryKey(StoreTurnover record);
}