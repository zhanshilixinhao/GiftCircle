package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.ElSendDetail;
import org.apache.ibatis.annotations.Param;

public interface ElSendDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ElSendDetail record);

    int insertSelective(ElSendDetail record);

    ElSendDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ElSendDetail record);

    int updateByPrimaryKey(ElSendDetail record);


    ElSendDetail selectByUserIdSendId(@Param("couponSendId") Integer couponSendId, @Param("userId") Integer userId);
}
