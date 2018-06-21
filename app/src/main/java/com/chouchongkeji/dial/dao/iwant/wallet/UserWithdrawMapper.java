package com.chouchongkeji.dial.dao.iwant.wallet;

import com.chouchongkeji.dial.pojo.iwant.wallet.UserWithdraw;
import com.chouchongkeji.service.iwant.wallet.vo.UserWithdrawVo;

import java.util.List;

public interface UserWithdrawMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserWithdraw record);

    int insertSelective(UserWithdraw record);

    UserWithdraw selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserWithdraw record);

    int updateByPrimaryKey(UserWithdraw record);

    /**
     * 用户提现记录
     *
     * @param: [userId 用户id]
     * @return: java.util.List<com.chouchongkeji.dial.pojo.iwant.wallet.UserWithdraw>
     * @author: yy
     * @Date: 2018/6/12
     */
    List<UserWithdrawVo> selectByUserId(Integer userId);
}