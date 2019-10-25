package com.chouchongkeji.service.v3;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.yichen.auth.service.UserDetails;

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
    Response getMemberCardList(UserDetails userDetails, PageQuery page);


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
     * 会员卡充值记录
     * @param userDetails
     * @param id 会员卡id
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    Response chargeRecordList(UserDetails userDetails, Integer id,PageQuery page);
}
