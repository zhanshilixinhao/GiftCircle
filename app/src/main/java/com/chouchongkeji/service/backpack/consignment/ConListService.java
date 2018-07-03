package com.chouchongkeji.service.backpack.consignment;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

/**
 * @author linqin
 * @date 2018/7/2
 */
public interface ConListService {
    /**
     * 查询寄售台某个商品列表
     *
     * @param targetId 目标物品id
     * @param type     1 商品 2虚拟物品 3 优惠券
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    Response getOneList(Integer targetId, Integer type , PageQuery pageQuery);

    /**
     * 寄售台商品列表
     *
     * @param pageQuery 分页
     * @return
     * @author linqin
     * @date 2018/7/3
     */
    Response getAllList( PageQuery pageQuery);

    /**
     * 寄售台商品详情
     * @param consignmentId 寄售台商品Id
     * @return
     * @author linqin
     * @date 2018/7/3
     */
    Response getItemDetail(Integer consignmentId);
}
