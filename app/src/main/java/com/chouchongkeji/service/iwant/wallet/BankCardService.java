package com.chouchongkeji.service.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;

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
}
