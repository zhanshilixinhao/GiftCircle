package com.chouchongkeji.mvc.controller.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.iwant.wallet.FireworksService;
import com.chouchongkeji.service.user.memo.MemoAffairService;
import com.chouchongkeji.util.TimeUtils;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @author linqin
 * @description 礼花、邀请好友
 * @date 2019/4/3
 */
@RestController
@RequestMapping("auth/v1/fireworks/")
public class FireworksController {

    @Autowired
    private FireworksService fireworksService;



    /**
     * 用户礼花详情
     *
     * @param details
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    @PostMapping("detail")
    public Response fireworksDetail(@AuthenticationPrincipal UserDetails details) {
        return fireworksService.fireworksDetail(details.getUserId());
    }


    /**
     * 我的团队
     *
     * @param userDetails
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    @PostMapping("user_list")
    public Response getInviteUserList(@AuthenticationPrincipal UserDetails userDetails) {
        return fireworksService.getInviteUserList(userDetails.getUserId());
    }


    /**
     * 礼花收益记录
     *
     * @param userDetails
     * @param starting    开始时间
     * @param ending      截至时间
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    @PostMapping("earn_record")
    public Response earnRecordList(@AuthenticationPrincipal UserDetails userDetails, PageQuery pageQuery,
                                   Long starting, Long ending) throws ParseException {
        if (starting != null) {
            starting = TimeUtils.time(starting);
        }
        if (ending != null) {
            ending = TimeUtils.timeEnd(ending);
        }
        return fireworksService.earnRecordList(userDetails.getUserId(), pageQuery, starting, ending);
    }


    /**
     * 礼花剩余数量
     *
     * @param userDetails
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    @PostMapping("number")
    public Response numberRemaining(@AuthenticationPrincipal UserDetails userDetails) {
        return fireworksService.numberRemaining(userDetails.getUserId());
    }


}
