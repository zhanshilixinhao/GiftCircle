package com.chouchongkeji.dial.dao.iwant.wallet;

import com.chouchongkeji.dial.pojo.iwant.wallet.Fireworks;

public interface FireworksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Fireworks record);

    int insertSelective(Fireworks record);

    Fireworks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Fireworks record);

    int updateByPrimaryKey(Fireworks record);

    /**
     * 查询该用户是否存在
     * @param userId
     * @return
     */
    Fireworks selectByUserId(Integer userId);
}