package com.chouchongkeji.service.v3;

import com.chouchongkeji.dial.pojo.v3.UserMemberCard;
import com.chouchongkeji.goexplore.common.Response;
import com.yichen.auth.mvc.AppClient;

import java.math.BigDecimal;

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

    /**
     * 添加会员卡使用详情记录
     *
     */
    int addStoreMountDetail(Integer userId,Integer merchantId,Integer storeId,BigDecimal rec,BigDecimal send,BigDecimal expense,
                             Byte type,String explain,BigDecimal total,Float scale,Integer cardId,BigDecimal balance,
                            Byte status,Integer eventId,Long orderNo);

    /**
     * 添加营业额记录
     * @param userId 用户id
     * @param cardId 会员卡id
     * @param expense 消费金额
     * @param storeMemberId  门店金额详情id
     * @param storeId 消费门店
     */
    void addTurnover(Integer userId,Integer cardId,BigDecimal expense,Integer storeMemberId,Integer storeId);

    /**
     * 更新详细记录
     * @param id 详细记录id
     * @param balance 余额
     * @param status 状态
     */
     void updateDetailCharge(Integer id,BigDecimal balance,Byte status);
    /**
     * 更新余额
     * @param userId 用户id
     * @param amount 充值金额+赠送金额
     * @param consume 消费金额
     */
    UserMemberCard updateBalance(Integer userId, BigDecimal amount, BigDecimal consume);

    /**
     * 添加会员卡消费记录
     * @param userId 用户id
     * @param amount 消费金额
     * @param targetId 目标id
     * @param explain 消费说明
     */
     void addExpenseRecord(Integer userId,BigDecimal amount,String targetId,String explain,Long orderNo,BigDecimal before);
}
