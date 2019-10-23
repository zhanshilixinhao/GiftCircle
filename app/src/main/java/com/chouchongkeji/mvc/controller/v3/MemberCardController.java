package com.chouchongkeji.mvc.controller.v3;

import com.chouchongkeji.dial.dao.v3.MemberCardMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.v3.MemberCardService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2019/10/23
 */
@RestController
@RequestMapping("auth/v3/memberCard")
public class MemberCardController {

    @Autowired
    private MemberCardService memberCardService;

    /**
     * 获取用户会员卡列表
     *
     * @param userDetails
     * @param page
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    @PostMapping("list")
    public Response getMemberCardList(@AuthenticationPrincipal UserDetails userDetails, PageQuery page) {
        return memberCardService.getMemberCardList(userDetails, page);
    }

}
