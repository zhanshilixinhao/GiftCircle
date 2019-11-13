package com.chouchongkeji.service.v3;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author linqin
 * @date 2019/11/13
 */

public interface CardDetailService {

    /**
     * 获取会员卡使用说明
     * @param id 会员卡id
     * @return
     */
    Response getCardDetail(Integer id);
}
