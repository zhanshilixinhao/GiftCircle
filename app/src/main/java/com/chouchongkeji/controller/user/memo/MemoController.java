package com.chouchongkeji.controller.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoActivity;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.DateUtil;
import com.chouchongkeji.goexplore.utils.Utils;
import com.chouchongkeji.service.user.memo.MemoService;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashSet;

/**
 * @author yichenshanren
 * @date 2018/6/22
 */

@RestController
@RequestMapping("auth/memo")
public class MemoController {

    @Autowired
    private MemoService memoService;

    /**
     * 添加活动
     *
     * @param userDetails 用户信息
     * @param date        备忘日期
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @PostMapping("activity/add")
    public Response addActivity(@AuthenticationPrincipal UserDetails userDetails,
                                MemoActivity activity, Long date) {
        if (StringUtils.isAnyBlank(activity.getAddress(), activity.getUsers(), activity.getDetail(),
                activity.getTitle()) || date == null || activity.getCount() == null) {
            return ResponseFactory.errMissingParameter();
        }
        // 判断日期是否正确
        Date targetTime = new Date(date);
        activity.setTargetTime(targetTime);
        // 判断时间，不能小于现在
        if (targetTime.getTime() < System.currentTimeMillis()) {
            return ResponseFactory.err("时间不能晚于当前时间!");
        }
        // 取出用户id
        HashSet<Integer> idSet = new HashSet<>();
        if (Utils.getIds(activity.getUsers(), idSet)) {
            return ResponseFactory.err("用户id错误!");
        }
        if (activity.getCount() != idSet.size()) {
            return ResponseFactory.err("人数和邀请的用户数不一致!");
        }
        return memoService.addActivity(userDetails.getUserId(), activity, idSet);
    }

}
