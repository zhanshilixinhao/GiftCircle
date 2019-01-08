package com.chouchongkeji.mvc.controller.user.memo;

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
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @PostMapping("activity/add")
    public Response addActivity(@AuthenticationPrincipal UserDetails userDetails,
                                MemoActivity activity) {
        if (StringUtils.isAnyBlank(activity.getAddress(), activity.getUsers(), activity.getDetail())
                || activity.getTargetTime() == null || activity.getCount() == null) {
            return ResponseFactory.errMissingParameter();
        }
        // 判断时间，不能小于现在
        if (activity.getTargetTime().getTime() < System.currentTimeMillis()) {
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
     * 修改活动信息
     *
     * @param userDetails 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @PostMapping("activity/modify")
    public Response modifyActivity(@AuthenticationPrincipal UserDetails userDetails,
                                   MemoActivity activity) {
        if (activity.getId() == null) {
            return ResponseFactory.errMissingParameter();
        }
        // 判断时间，不能小于现在
        if (activity.getTargetTime() != null && activity.getTargetTime().getTime() < System.currentTimeMillis()) {
            return ResponseFactory.err("时间不能晚于当前时间!");
        }
        HashSet<Integer> idSet = null;
        if (StringUtils.isNotBlank(activity.getUsers())) {
            // 取出用户id
            idSet = new HashSet<>();
            if (Utils.getIds(activity.getUsers(), idSet)) {
                return ResponseFactory.err("用户id错误!");
            }
            if (activity.getCount() != idSet.size()) {
                return ResponseFactory.err("人数和邀请的用户数不一致!");
            }
        }
        return memoService.modifyActivity(userDetails.getUserId(), activity, idSet);
    }


    /**
     * 添加事件
     *
     * @param userDetails 用户信息
     * @param event       事件数据
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @PostMapping("event/add")
    public Response addEvent(@AuthenticationPrincipal UserDetails userDetails,
                             MemonEvent event) {
        if (StringUtils.isBlank(event.getDetail()) || event.getTargetTime() == null || event.getEventTime() == null) {
            return ResponseFactory.errMissingParameter();
        }

        if (event.getIsPublic() == null || event.getIsPublic() < 0 || event.getIsPublic() > 2) {
            event.setIsPublic((byte) 2);
        }
        return memoService.addEvent(userDetails.getUserId(), event);
    }


    /**
     * 修改事件
     *
     * @param userDetails 用户信息
     * @param event       事件数据
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @PostMapping("event/modify")
    public Response modifyEvent(@AuthenticationPrincipal UserDetails userDetails,
                                MemonEvent event) {
        if (event.getId() == null) {
            return ResponseFactory.errMissingParameter();
        }

        if (event.getIsPublic() != null && event.getIsPublic() != 1 && event.getIsPublic() != 2) {
            event.setIsPublic((byte) 2);
        }
        return memoService.modifyEvent(userDetails.getUserId(), event);
    }


    /**
     * 活动列表
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

    /**
     * 获得好友的备忘录
     *
     * @param userDetails 用户信息
     * @param start       开始时间
     * @param end         结束时间
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @PostMapping("list/friend")
    public Response getListForFriend(@AuthenticationPrincipal UserDetails userDetails,
                                     Integer friendUserId,
                                     Long start, Long end) {
        if (friendUserId == null) return ResponseFactory.errMissingParameter();
        if (start == null) {
            start = 0L;
        }
        if (end == null) {
            end = 0L;
        }
        return memoService.getListForFriend(userDetails.getUserId(), start, end, friendUserId);
    }

    /**
     * 查看备忘录详情
     *
     * @param userDetails 用户信息
     * @param id          备忘录 活动 | 事件 id
     * @param type        类型 1 活动 2 事件
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @PostMapping("detail")
    public Response getDetail(@AuthenticationPrincipal UserDetails userDetails,
                              Integer id, Integer type) {
        if (id == null || type == null || type != 1 && type != 2) {
            return ResponseFactory.errMissingParameter();
        }
        return memoService.getDetail(userDetails.getUserId(), id, type);
    }

    /**
     * 删除一个备忘录信息
     *
     * @param userDetails 用户信息
     * @param id          备忘录 活动 | 事件 id
     * @param type        类型 1 活动 2 事件
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @PostMapping("del")
    public Response delMemo(@AuthenticationPrincipal UserDetails userDetails,
                            Integer id, Integer type) {
        if (id == null || type == null || type != 1 && type != 2) {
            return ResponseFactory.errMissingParameter();
        }
        return memoService.delMemo(userDetails.getUserId(), id, type);
    }


    /**
     * 首页的三个备忘录
     *
     * @param userDetails 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/6/22
     */
    @PostMapping("home")
    public Response getHomeList(@AuthenticationPrincipal UserDetails userDetails) {
        return memoService.getHomeList(userDetails.getUserId());
    }
}
