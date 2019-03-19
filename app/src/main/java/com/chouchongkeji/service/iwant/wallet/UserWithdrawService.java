package com.chouchongkeji.service.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.iwant.wallet.vo.UserBankCardVo;
import com.chouchongkeji.service.iwant.wallet.vo.UserBankVo2;

import java.math.BigDecimal;

public interface UserWithdrawService {
    /**
     * 添加用户提现记录
     *
     * @param: [userId 用户id, id 用户银行卡id, amount 提现金额]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/8
     */
    Response addUserWithdraw(Integer userId, Integer id, BigDecimal amount);

    /**
     * 获得用户的提现记录
     *
     * @param: [userId 用户id, pageQuery 分页信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    Response getUserWithdrawList(Integer userId, PageQuery pageQuery);

    /**
     * 小程序背包物品折现
     *
     * @param: [details 用户认证信息, bpId 背包id, bankCard 银行信息 ]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/8
     */
    Response addWXBpWithdraw(Integer userId, Long bpId, Byte type, UserBankVo2 bankCard);

    /**
     * 微信折现 记录
     *
     * @param userId
     * @return
     */
    Response getWxRecords(Integer userId);
}
