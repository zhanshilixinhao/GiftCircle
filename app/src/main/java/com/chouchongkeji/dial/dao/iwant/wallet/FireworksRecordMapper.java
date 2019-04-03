package com.chouchongkeji.dial.dao.iwant.wallet;

import com.chouchongkeji.dial.pojo.iwant.wallet.FireworksRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FireworksRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FireworksRecord record);

    int insertSelective(FireworksRecord record);

    FireworksRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FireworksRecord record);

    int updateByPrimaryKey(FireworksRecord record);

    /**
     * 查询礼花收益记录（时间）
     * @return
     */
    List<FireworksRecord> selectByUserIdAndTime(@Param("userId") Integer userId,@Param("starting") Long starting,@Param("ending") Long ending);


}