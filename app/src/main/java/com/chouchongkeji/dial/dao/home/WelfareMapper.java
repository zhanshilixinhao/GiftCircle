package com.chouchongkeji.dial.dao.home;

import com.chouchongkeji.dial.pojo.home.Welfare;

import java.util.List;

public interface WelfareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Welfare record);

    int insertSelective(Welfare record);

    Welfare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Welfare record);

    int updateByPrimaryKey(Welfare record);

    Welfare selectByTime();

    /**
     * 获取福利商品详情
     * @return
     */
    Welfare selectAllByTime();
}
