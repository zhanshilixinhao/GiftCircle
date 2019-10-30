package com.chouchongkeji.service.v3;

import com.chouchongkeji.goexplore.common.Response;
import com.yichen.auth.mvc.AppClient;

/**
 * @author linqin
 * @date 2019/10/29
 */

public interface ChargeCardService {

    /**
     * 礼遇圈会员卡充值规则
     *
     * @return
     * @author linqin
     * @date 2019/10/29
     */
    Response getChargeRuleList();


    /**
     * 创建礼遇圈会员卡充值订单
     *
     * @param userId
     * @param eventId     活动id
     * @return
     * @author linqin
     * @date 2019/10/29
     */
    Response createdOrder(Integer userId, Integer client, Integer eventId, Integer payWay);


    /**
     * 礼遇圈会员卡支付成功后的业务逻辑
     *
     * @return
     */
     void successPay(Long orderNo);
}
