package com.chouchongkeji.service.backpack.consignment;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author linqin
 * @date 2018/7/2
 */

public interface ConsignmentService {
    /**
     * 上架寄售台之前商品信息查询
     *
     * @param userId  用户Id
     * @param bpId    背包id
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    Response getInfo(Integer userId, Integer bpId);
}
