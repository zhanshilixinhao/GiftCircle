package com.chouchongkeji.dao.user;

import com.chouchongkeji.pojo.user.UserThird;
import org.apache.ibatis.annotations.Param;

public interface UserThirdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserThird record);

    int insertSelective(UserThird record);

    UserThird selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserThird record);

    int updateByPrimaryKey(UserThird record);

    UserThird selectByOpenIdAndType(@Param("openId") String openId,@Param("type") int type);
}