package com.chouchongkeji.service.backpack.consignment;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2018/7/2
 */

public interface ConsignmentService {
    /**
     * 上架寄售台之前商品信息查询
     *
     * @param userId 用户Id
     * @param bpId   背包id
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    Response getInfo(Integer userId, Long bpId);

    /**
     * 上架寄售台
     *
     * @param userId 用户Id
     * @param bpId   背包Id
     * @param price  商品上架价格
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    Response putawayItem(Integer userId, Long bpId, BigDecimal price);

    /**
     * 寄售台卖家/买家列表
     *
     * @param userId    用户Id
     * @param user      卖家/买家  1-卖家，2-买家
     * @param condition 商家订单状态 1-全部 ，2-交易中，3-已完成
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    Response buySellList(Integer userId, Byte user, Byte condition, PageQuery pageQuery);
}
