package com.chouchongkeji.mvc.controller.v3;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
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

    /**
     * 会员卡详情
     *
     * @param userDetails
     * @param id          用户会员卡关联id
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    @PostMapping("detail")
    public Response detailMemberCard(@AuthenticationPrincipal UserDetails userDetails, Integer id) {
        if (id == null) {
            return ResponseFactory.errMissingParameter();
        }
        return memberCardService.detailMemberCard(userDetails, id);
    }

    /**
     * 会员卡充值记录
     * @param userDetails
     * @param id 会员卡id
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    @PostMapping("charge/record")
    public Response chargeRecordList(@AuthenticationPrincipal UserDetails userDetails,Integer id,PageQuery page) {
        if (id == null){
            return ResponseFactory.errMissingParameter();
        }
        return memberCardService.chargeRecordList(userDetails,id,page);
    }


    /**
     * 会员卡充值记录详情
     * @param userDetails
     * @param id 会员卡充值记录id
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    @PostMapping("charge/detail")
    public Response chargeRecordDetail(@AuthenticationPrincipal UserDetails userDetails,Integer id) {
        if (id == null){
            return ResponseFactory.errMissingParameter();
        }
        return memberCardService.chargeRecordDetail(userDetails,id);
    }


    /**
     * 会员卡消费记录
     * @param userDetails
     * @param id 会员卡id
     * @param page
     * @return
     */
    @PostMapping("expense/record")
    public Response expenseRecordList(@AuthenticationPrincipal UserDetails userDetails,Integer id,PageQuery page){
        if (id == null){
            return ResponseFactory.errMissingParameter();
        }
        return memberCardService.expenseRecordList(userDetails.getUserId(),id,page);
    }


    /**
     * 会员卡消费记录详情
     * @param userDetails
     * @param id 会员卡消费记录id
     * @return
     */
    @PostMapping("expense/detail")
    public Response expenseRecordDetail(@AuthenticationPrincipal UserDetails userDetails,Integer id){
        if (id == null){
            return ResponseFactory.errMissingParameter();
        }
        return memberCardService.expenseRecordDetail(userDetails.getUserId(),id);
    }


}