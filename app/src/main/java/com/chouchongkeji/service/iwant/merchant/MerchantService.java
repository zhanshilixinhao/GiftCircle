package com.chouchongkeji.service.iwant.merchant;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.iwant.merchant.vo.MerchantVo;

public interface MerchantService {
    /**
     * 商家认证申请
     *
     * @param: [userId 用户id,merchantVo 商家信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    Response applyMerchant(Integer userId, MerchantVo merchantVo);

    /**
     * 获得该用户的商家认证状态
     *
     * @param: [userId 用户id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    Response getMerchantStatus(Integer userId);
}
