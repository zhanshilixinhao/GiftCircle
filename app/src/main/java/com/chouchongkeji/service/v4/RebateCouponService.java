package com.chouchongkeji.service.v4;

import com.chouchongkeji.dial.pojo.v4.RebateCoupon;
import com.chouchongkeji.goexplore.common.Response;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/10/21 11:15
 */
public interface RebateCouponService {
    /**
     * @Description: 創建折扣劵
     * @Author: LxH
     * @Date: 2020/10/21 11:23
     */
    Response createRebateCoupon(Integer userId,Integer storeId);
    /**
     * @Description: 生成核销码
     * @Author: LxH
     * @Date: 2020/10/21 15:18
     */
    Response appliedRebateCoupon(Integer userId, Long rebateCouponId);

    Response addRebate(BigDecimal userRebate, Integer userId, Integer superId,Integer storeId,BigDecimal rebateTopLimit);

    /**
     * @Description: 查询所有店铺
     * @Author: LxH
     * @Date: 2020/10/26 12:15
     */
    Response findStores();

    /**
     * @Description: 获取上级用户信息
     * @Author: LxH
     * @Date: 2020/11/16 11:43
     */
    Response getSuperUserInfo(Integer superId,Integer storeId);
}
