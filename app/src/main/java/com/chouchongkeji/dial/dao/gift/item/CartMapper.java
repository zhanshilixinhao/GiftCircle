package com.chouchongkeji.dial.dao.gift.item;

import com.chouchongkeji.dial.pojo.gift.item.Cart;
import com.chouchongkeji.service.mall.item.vo.CartListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<CartListVo> selectByUserId(Integer userId);

    Cart selectBySkuIAndUserId(@Param("userId") Integer userId,@Param("skuId") Integer skuId);

    int deleteByUserIdAndskuId(@Param("userId") Integer userId,@Param("skuId") Integer skuId);
}