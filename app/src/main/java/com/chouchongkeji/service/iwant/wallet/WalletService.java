package com.chouchongkeji.service.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author yy
 * @date 2018/6/5
 **/
public interface WalletService {
    /**
     * 获取钱包详情
     * @param userId 用户Id
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    Response getWalletDetail(Integer userId);
}
