package com.chouchongkeji.dao.user;

import com.chouchongkeji.pojo.user.ThirdAccount;

public interface ThirdAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThirdAccount record);

    int insertSelective(ThirdAccount record);

    ThirdAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThirdAccount record);

    int updateByPrimaryKey(ThirdAccount record);
}