package com.chouchongkeji.mvc.controller.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoAffair;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.Utils;
import com.chouchongkeji.service.user.memo.AffairService;
import com.chouchongkeji.service.user.memo.MemoAffairService;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashSet;

/**
 * @author linqin
 * @date 2019/3/11 16:19
 */
@RestController
@RequestMapping("auth/memo/affair2")
public class AffairController {

    @Autowired
    private AffairService affairService;

    @Autowired
    private MemoAffairService memoAffairService;

    /**
     * 添加备忘录事件类型
     *
     * @param userDetails
     * @param name        类型名称
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    @PostMapping("event/add")
    public Response addEventType(@AuthenticationPrincipal UserDetails userDetails, String name) {
        if (StringUtils.isBlank(name)) {
            return ResponseFactory.errMissingParameter();
        }
        return affairService.addEventType(userDetails.getUserId(), name);
    }


    /**
     * 修改备忘录事件类型
     *
     * @param userDetails
     * @param name        类型名称
     * @param eventId     事件类型id
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    @PostMapping("event/update")
    public Response updateEventType(@AuthenticationPrincipal UserDetails userDetails, String name, Integer eventId) {
        if (eventId == null) {
            return ResponseFactory.errMissingParameter();
        }
        if (StringUtils.isBlank(name)) {
            return ResponseFactory.errMissingParameter();
        }
        return affairService.updateEventType(userDetails.getUserId(), name, eventId);
    }


    /**
     * 备忘录事件类型列表
     *
     * @param userDetails
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    @PostMapping("event/list")
    public Response getEventTypeList(@AuthenticationPrincipal UserDetails userDetails) {
        return affairService.getEventTypeList(userDetails.getUserId());
    }


    /**
     * 删除备忘录事件类型
     *
     * @param userDetails
     * @param eventId     事件类型id
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    @PostMapping("event/del")
    public Response deleteEventType(@AuthenticationPrincipal UserDetails userDetails, Integer eventId) {
        if (eventId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return affairService.deleteEventType(userDetails.getUserId(), eventId);
    }


    /**
     * 添加事件
     *
     * @param userDetails
     * @param memoAffair
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    @PostMapping("add")
    public Response addAffair(@AuthenticationPrincipal UserDetails userDetails, MemoAffair memoAffair, Byte isCirculation) {
        if (StringUtils.isAnyBlank(memoAffair.getDetail()) || memoAffair.getTargetTime() == null || memoAffair.getEventTypeId() == null) {
            return ResponseFactory.errMissingParameter();
        }
        if (isCirculation == null) {
            isCirculation = 0;
        } else if (isCirculation < 0 || isCirculation > 3) {
            return ResponseFactory.err("参数错误");
        }
        HashSet<Integer> idSet = null;
        if (StringUtils.isNotBlank(memoAffair.getUsers())) {
            // 取出用户id
            idSet = new HashSet<>();
            if (Utils.getIds(memoAffair.getUsers(), idSet)) {
                return ResponseFactory.err("用户id错误!");
            }
        }
        return affairService.addAffair(userDetails.getUserId(), memoAffair, idSet, isCirculation);
    }


    /**
     * 修改备忘录事件
     *
     * @param userDetails
     * @param affair
     * @return
     * @author linqin
     * @date 2019/3/12
     */
    @PostMapping("modify_affair")
    public Response modifyAffair(@AuthenticationPrincipal UserDetails userDetails, MemoAffair affair) {
        if (affair.getId() == null || affair.getTargetTime() == null || StringUtils.isBlank(affair.getDetail()) || affair.getEventTypeId() == null) {
            return ResponseFactory.errMissingParameter();
        }
        HashSet<Integer> idSet = null;
        if (StringUtils.isNotBlank(affair.getUsers())) {
            // 取出用户id
            idSet = new HashSet<>();
            if (Utils.getIds(affair.getUsers(), idSet)) {
                return ResponseFactory.err("用户id错误!");
            }
        }
        return affairService.modifyAffair(userDetails.getUserId(), affair, idSet);
    }


    /**
     * 删除一个备忘录信息
     *
     * @param userDetails 用户信息
     * @param id          备忘录 id
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @PostMapping("del")
    public Response delMemo(@AuthenticationPrincipal UserDetails userDetails,
                            Integer id) {
        if (id == null) {
            return ResponseFactory.errMissingParameter();
        }
        return affairService.delMemo(userDetails.getUserId(), id);
    }


    /**
     * 获取备忘录列表
     *
     * @param userDetails
     * @param start
     * @param end
     * @return
     * @author linqin
     * @date 2019/1/7 16:38
     */
    @PostMapping("list")
    public Response getAffairList(@AuthenticationPrincipal UserDetails userDetails, Long start, Long end) throws ParseException {
        if (start == null) {
            start = 0L;
        } else {
            start = memoAffairService.time(start);
        }
        if (end == null) {
            end = 0L;
        } else {
            end = memoAffairService.timeEnd(end);
        }
        return affairService.getAffairList(userDetails.getUserId(), start, end);
    }


    /**
     * 获得好友的备忘录
     *
     * @param userDetails 用户信息
     * @param start       开始时间
     * @param end         结束时间
     * @return
     * @author linqin
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
        return affairService.getListForFriend(userDetails.getUserId(), start, end, friendUserId);
    }

    /**
     * 节日事件详情
     *
     * @param id 节日事件id
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @PostMapping("festival_detail")
    public Response memoFestivalDetail(@AuthenticationPrincipal UserDetails userDetails,Integer id) {
        if (id == null) {
            return ResponseFactory.errMissingParameter();
        }
        return affairService.memoFestivalDetail(userDetails.getUserId(),id);
    }


    /**
     * 节日事件详情页商品列表
     *
     * @param id 节日事件id
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @PostMapping("festival_item")
    public Response memoFestivalDetailItems(@AuthenticationPrincipal UserDetails userDetails, Integer id) {
        if (id == null) {
            return ResponseFactory.errMissingParameter();
        }
        return affairService.memoFestivalDetailItems(userDetails.getUserId(),id);
    }


    /**
     * 节日详情页好友列表
     *
     * @param userDetails
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @PostMapping("friend_list")
    public Response getFriendList(@AuthenticationPrincipal UserDetails userDetails) {
        return affairService.getFriendList(userDetails.getUserId());
    }


}
