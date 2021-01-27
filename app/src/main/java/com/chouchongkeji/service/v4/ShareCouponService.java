package com.chouchongkeji.service.v4;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

import javax.annotation.Resource;

public interface ShareCouponService {
    /**
     *
     *
     *@description: 用户领取分享劵
     *@author: LxH
     *@time: 2020/10/15 0015 下午 6:20
     *
     */
    Response giveShareCoupon(Integer userId, Integer shareCouponId,Integer storeId);
    /**
     *
     *
     *@description: 获取用户分享劵列表
     *@author: LxH
     *@time: 2020/10/16 0016 上午 11:28
     *
     */
    Response getShareCouponList(Integer userId);

    /**
     * @Description: 获取拥有分享券的店铺
     * @Author: LxH
     * @Date: 2020/10/22 14:44
     */
    Response findStore();

    /**
     * @Description: 查找分享
     * @Author: LxH
     * @Date: 2020/10/22 15:45
     */
    Response findShareCouponByStore(Integer storeId);

    Response findShareCoupon(Integer shareCouponId);
}
