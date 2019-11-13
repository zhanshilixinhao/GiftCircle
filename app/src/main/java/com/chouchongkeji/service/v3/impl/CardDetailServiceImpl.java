package com.chouchongkeji.service.v3.impl;

import com.chouchongkeji.dial.dao.v3.MembershipCardMapper;
import com.chouchongkeji.dial.pojo.v3.MembershipCard;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.v3.CardDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linqin
 * @date 2019/11/13
 */
@Service
public class CardDetailServiceImpl implements CardDetailService {

    @Autowired
    private MembershipCardMapper membershipCardMapper;

    /**
     * 获取会员卡使用说明
     * @param id 会员卡id
     * @return
     */
    @Override
    public Response getCardDetail(Integer id) {
        MembershipCard membershipCard = membershipCardMapper.selectByPrimaryKey(id);
        if (membershipCard == null){
            return ResponseFactory.err("无此会员卡");
        }
        return ResponseFactory.sucData(membershipCard.getSummary());
    }


}
