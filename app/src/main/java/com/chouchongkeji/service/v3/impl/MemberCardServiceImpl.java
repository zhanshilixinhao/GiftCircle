package com.chouchongkeji.service.v3.impl;

import com.chouchongkeji.dial.dao.v3.UserMemberCardMapper;
import com.chouchongkeji.dial.pojo.v3.UserMemberCard;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.v3.MemberCardService;
import com.chouchongkeji.service.v3.vo.CardVo;
import com.github.pagehelper.PageHelper;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linqin
 * @date 2019/10/23
 */
@Service
public class MemberCardServiceImpl implements MemberCardService {

    @Autowired
    private UserMemberCardMapper userMemberCardMapper;

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
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<CardVo> cardVos = userMemberCardMapper.selectByUserId(userDetails.getUserId());
        return null;
    }
}
