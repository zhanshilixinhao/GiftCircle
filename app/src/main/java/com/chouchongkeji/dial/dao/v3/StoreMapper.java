package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.Store;
import com.chouchongkeji.service.v4.vo.StoreVoV4;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);

    StoreVoV4 findStore(Integer storeId);


    @Select("SELECT create_admin_id FROM sys_admin WHERE id = #{adminId}")
    Integer findAdmin(Integer adminId);

    @Select("SELECT * FROM store")
    List<Store> findStores();
}