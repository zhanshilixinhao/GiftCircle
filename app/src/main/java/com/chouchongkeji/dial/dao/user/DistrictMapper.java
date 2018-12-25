package com.chouchongkeji.dial.dao.user;

import com.chouchongkeji.dial.pojo.user.District;
import com.chouchongkeji.service.user.info.vo.DistrictVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer adcode);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Integer adcode);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    List<District> selectByLevelPAdcode(@Param("level") String level,@Param("pAdcode") Integer pAdcode);

    List<DistrictVo> selectSimple(@Param("level") String level,@Param("pAdcode") Integer pAdcode);
}
