package com.chouchongkeji.service.v3.impl;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.v3.MemberCardService;
import com.yichen.auth.service.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author linqin
 * @date 2019/10/23
 */
@Service
public class MemberCardServiceImpl implements MemberCardService {

    /**
     * 获取用户会员卡列表
     *
     * @param userDetails
     * @param page
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    @Override
    public Response getMemberCardList(UserDetails userDetails, PageQuery page) {
        return null;
    }
}
