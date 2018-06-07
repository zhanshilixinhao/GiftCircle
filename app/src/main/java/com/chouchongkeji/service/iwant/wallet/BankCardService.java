package com.chouchongkeji.service.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.iwant.wallet.vo.UserBankCardVo;

public interface BankCardService {

    /**
     * 获得可用的银行
     *
     * @param: []
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/5
     */
    Response getBankList();

    /**
     * 获得用户所有的银行卡列表
     *
     * @param: [userId 用户id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/5
     */
    Response getUserBankCardList(Integer userId);

    /**
     * 删除用户银行卡
     *
     * @param: [userId 用户id, id 银行卡id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    Response delUserBankCard(Integer userId, Integer id);

    /**
     * 添加用户银行卡
     *
     * @param: [userId 用户id, userBankCardVo 银行卡信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/7
     */
    Response addUserBankCard(Integer userId, UserBankCardVo userBankCardVo);
}
