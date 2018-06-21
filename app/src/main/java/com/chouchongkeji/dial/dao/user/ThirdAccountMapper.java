package com.chouchongkeji.dial.dao.user;

import com.chouchongkeji.dial.pojo.user.ThirdAccount;

public interface ThirdAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThirdAccount record);

    int insertSelective(ThirdAccount record);

    ThirdAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThirdAccount record);

    int updateByPrimaryKey(ThirdAccount record);

    ThirdAccount selectByOpenIdAndType(String openId, int type);
}