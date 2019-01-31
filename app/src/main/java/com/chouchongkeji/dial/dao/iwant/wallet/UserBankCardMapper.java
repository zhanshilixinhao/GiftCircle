package com.chouchongkeji.dial.dao.iwant.wallet;

import com.chouchongkeji.dial.pojo.iwant.wallet.UserBankCard;
import com.chouchongkeji.service.iwant.wallet.vo.UserBankCardVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserBankCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBankCard record);

    int insertSelective(UserBankCard record);

    UserBankCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBankCard record);

    int updateByPrimaryKey(UserBankCard record);

    /**
     * 查询用户银行卡列表
     *
     * @param: [userId 用户id, status 银行卡状态]
     * @return: java.util.List<com.chouchongkeji.service.iwant.wallet.vo.UserBankCardVo>
     * @author: yy
     * @Date: 2018/6/6
     */
    List<UserBankCardVo> getUserBankCardList(@Param("userId") Integer userId, @Param("status")Integer status);

    /**
     * 把原来提现的银行卡设为不是默认2
     * @param userId
     * @return
     */
    int updateIsDefault(Integer userId);

    /**
     * 取出用户银行卡信息
     * @param id
     * @return
     */
    UserBankCard selectByBankId(Integer id);
}
