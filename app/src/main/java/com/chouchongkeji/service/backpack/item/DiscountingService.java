package com.chouchongkeji.service.backpack.item;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author linqin
 * @date 2018/7/11
 */
public interface DiscountingService {
    /**
     * 背包物品折现
     *
     * @param userId 用户id
     * @param bpId        背包id
     * @return
     * @author linqin
     * @date 2018/7/11
     */
    Response addDiscountRecord(Integer userId, Long bpId);
}
