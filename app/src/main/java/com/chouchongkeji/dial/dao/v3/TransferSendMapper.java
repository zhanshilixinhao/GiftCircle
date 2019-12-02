package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.TransferSend;
import com.chouchongkeji.service.v3.vo.TransferSendVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransferSendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TransferSend record);

    int insertSelective(TransferSend record);

    TransferSend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TransferSend record);

    int updateByPrimaryKey(TransferSend record);

    /**
     * 转赠列表
     * @param userId
     * @param cardId
     * @return
     */
    List<TransferSendVo> selectByUserIdCardId(@Param("userId") Integer userId, @Param("cardId") Integer cardId);
}
