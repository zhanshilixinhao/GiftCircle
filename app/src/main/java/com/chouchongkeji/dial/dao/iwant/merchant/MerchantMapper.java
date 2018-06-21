package com.chouchongkeji.dial.dao.iwant.merchant;

import com.chouchongkeji.dial.pojo.iwant.merchant.Merchant;
import org.apache.ibatis.annotations.Param;

public interface MerchantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    Merchant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Merchant record);

    int updateByPrimaryKey(Merchant record);

    /**
     * 查询用户是否申请过商家认证
     *
     * @param: [userId]
     * @return: com.chouchongkeji.dial.pojo.iwant.merchant.Merchant
     * @author: yy
     * @Date: 2018/6/20
     */
    Merchant selectByUserId(@Param("userId") Integer userId);
}