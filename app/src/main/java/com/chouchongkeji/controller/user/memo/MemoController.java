package com.chouchongkeji.controller.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoActivity;
import com.chouchongkeji.dial.pojo.user.memo.MemonEvent;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
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
        if (StringUtils.isAnyBlank(activity.getAddress(), activity.getUsers(), activity.getDetail())
                || date == null || activity.getCount() == null) {
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


    /**
     * 添加活动
     *
     * @param userDetails 用户信息
     * @param event       事件数据
     * @param eventDate   事件时间
     * @param targetDate  备忘时间
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @PostMapping("event/add")
    public Response addEvent(@AuthenticationPrincipal UserDetails userDetails,
                             MemonEvent event,
                             Long eventDate, Long targetDate) {
        if (StringUtils.isBlank(event.getDetail()) || eventDate == null || targetDate == null) {
            return ResponseFactory.errMissingParameter();
        }
        event.setEventTime(new Date(eventDate));
        event.setTargetTime(new Date(targetDate));
        if (event.getIsPublic() == null || event.getIsPublic() < 0 || event.getIsPublic() > 2) {
            event.setIsPublic((byte) 2);
        }
        return memoService.addEvent(userDetails.getUserId(), event);
    }

    /**
     * 添加活动
     *
     * @param userDetails 用户信息
     * @param start       开始时间
     * @param end         结束时间
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @PostMapping("list")
    public Response getList(@AuthenticationPrincipal UserDetails userDetails,
                             Long start, Long end) {
        if (start == null) {
            start = 0L;
        }
        if (end == null) {
            end = 0L;
        }
        return memoService.getList(userDetails.getUserId(), start, end);
    }
}
