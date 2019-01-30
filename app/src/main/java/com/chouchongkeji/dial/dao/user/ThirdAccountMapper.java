package com.chouchongkeji.dial.dao.user;

import com.chouchongkeji.dial.pojo.user.ThirdAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThirdAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThirdAccount record);

    int insertSelective(ThirdAccount record);

    ThirdAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThirdAccount record);

    int updateByPrimaryKey(ThirdAccount record);

    ThirdAccount selectByOpenIdAndType(@Param("openId") String openId,
                                       @Param("type") int type);

    List<ThirdAccount> selectByPhone(String phone);
}
