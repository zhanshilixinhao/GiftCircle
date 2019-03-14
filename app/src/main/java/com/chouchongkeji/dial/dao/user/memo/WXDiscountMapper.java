package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.WXDiscount;
import com.chouchongkeji.service.iwant.wallet.vo.WXDiscountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WXDiscountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WXDiscount record);

    int insertSelective(WXDiscount record);

    WXDiscount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WXDiscount record);

    int updateByPrimaryKey(WXDiscount record);

    List<WXDiscountVo> selectByUserId(@Param("userId") Integer userId);
}
