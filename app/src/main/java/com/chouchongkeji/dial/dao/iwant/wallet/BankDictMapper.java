package com.chouchongkeji.dial.dao.iwant.wallet;

import com.chouchongkeji.dial.pojo.iwant.wallet.BankDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankDictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankDict record);

    int insertSelective(BankDict record);

    BankDict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankDict record);

    int updateByPrimaryKey(BankDict record);

    /**
     * 通过状态获得银行列表
     *
     * @param: [status 银行是否可用状态]
     * @return: java.util.List<com.chouchongkeji.dial.pojo.iwant.wallet.BankDict>
     * @author: yy
     * @Date: 2018/6/5
     */
    List<BankDict> selectByStatus(@Param("status") Integer status);
}