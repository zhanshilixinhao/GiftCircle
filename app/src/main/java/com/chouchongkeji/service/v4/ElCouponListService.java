package com.chouchongkeji.service.v4;

import com.chouchongkeji.goexplore.common.Response;

public interface ElCouponListService {

    /**
     *
     *
     *@description: 用户领取礼包
     *@author: LxH
     *@time: 2020/10/17 0017 下午 3:51
     *
     */
    Response bindElCouponListByUser(Integer userId, Integer elCouponListId, Byte thePaid);

    /**
     * @Description: 获取获取礼包
     * @Author: LxH
     * @Date: 2020/10/22 16:23
     */
    Response getStore(Integer userMemberCardId);

    void sendElCouponList(Integer userId);

    /**
     * @Description: 扫码获取礼包详情
     * @Author: LxH
     * @Date: 2020/10/26 13:01
     */
    Response getElCouponListById(Integer id);

    /**
     * @Description: 查询是否弹礼包弹窗
     * @Author: LxH
     * @Date: 2021/1/25 9:15
     */
    Response queryState(Integer userId, Integer elCouponListId);
}
