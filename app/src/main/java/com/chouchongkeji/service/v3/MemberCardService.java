package com.chouchongkeji.service.v3;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.yichen.auth.service.UserDetails;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2019/10/23
 */

public interface MemberCardService {

    /**
     * 获取用户会员卡列表
     *
     * @param userDetails
     * @param page
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    Response getMemberCardList(UserDetails userDetails, PageQuery page,String keywords);


    /**
     * 会员卡详情
     *
     * @param userDetails
     * @param id          用户会员卡关联id
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    Response detailMemberCard(UserDetails userDetails, Integer id);


    /**
     * 给用户添加礼遇圈卡 （礼遇圈卡storeId也为0）
     *
     * @param userId 用户id
     * @return
     */
    int addMemberShipCard(Integer userId, BigDecimal balance, BigDecimal total, BigDecimal consume);

    /**
     * 会员卡充值记录
     * @param userDetails
     * @param id 会员卡id
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    Response chargeRecordList(UserDetails userDetails, Integer id,PageQuery page);

    /**
     * 会员卡充值记录详情
     * @param userDetails
     * @param id 会员卡充值记录id
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    Response chargeRecordDetail(UserDetails userDetails, Integer id);

    /**
     * 会员卡消费记录
     * @param userId
     * @param id 会员卡id
     * @param page
     * @return
     */
    Response expenseRecordList(Integer userId, Integer id, PageQuery page);

    /**
     * 会员卡消费记录详情
     * @param userId
     * @param id 会员卡消费记录id
     * @return
     */
    Response expenseRecordDetail(Integer userId, Integer id);
}
