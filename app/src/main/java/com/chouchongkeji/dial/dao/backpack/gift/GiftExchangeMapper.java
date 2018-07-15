package com.chouchongkeji.dial.dao.backpack.gift;

import com.chouchongkeji.dial.pojo.backpack.gift.GiftExchange;
import com.chouchongkeji.service.backpack.gift.vo.GiftExDetailVo;
import com.chouchongkeji.service.backpack.gift.vo.GiftExListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GiftExchangeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftExchange record);

//    int insertSelective(GiftExchange record);

    GiftExchange selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftExchange record);

    int updateByPrimaryKey(GiftExchange record);

    GiftExchange selectByUserIdAndId(@Param("usrId") Integer userId,@Param("id") Integer id);

    GiftExchange selectByUserIdGiftExId(@Param("usrId") Integer userId,@Param("giftExchangeId") Integer giftExchangeId);

    List<GiftExListVo> selectListByUserIdStatus(@Param("usrId") Integer userId,@Param("status") Byte status);

    GiftExDetailVo selectByUserIdgiftEXchangeId(@Param("usrId") Integer userId, @Param("giftExchangeId") Integer giftExchangeId);
}