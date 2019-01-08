package com.chouchongkeji.mvc.controller.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoAffair;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.Utils;
import com.chouchongkeji.service.user.memo.MemoAffairService;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

/**
 * @author linqin
 * @date 2019/1/7 16:38
 */
@RestController
@RequestMapping("auth/memo/affair")
public class MemoAffairController {

    @Autowired
    private MemoAffairService memoAffairService;

    /**
     * 添加备忘录事件
     *
     * @param userDetails
     * @param affair
     * @return
     * @author linqin
     * @date 2019/1/7 16:38
     */
    @PostMapping("add")
    public Response addAffair(@AuthenticationPrincipal UserDetails userDetails, MemoAffair affair) {
        if (StringUtils.isAnyBlank(affair.getDetail()) || affair.getTargetTime() == null) {
            return ResponseFactory.errMissingParameter();
        }
        // 判断时间，不能小于现在
        if (affair.getTargetTime().getTime() < System.currentTimeMillis()) {
            return ResponseFactory.err("时间不能晚于当前时间!");
        }
        HashSet<Integer> idSet = null;
        if (StringUtils.isNotBlank(affair.getUsers())) {
            // 取出用户id
            idSet = new HashSet<>();
            if (Utils.getIds(affair.getUsers(), idSet)) {
                return ResponseFactory.err("用户id错误!");
            }
        }
        return memoAffairService.addAffair(userDetails.getUserId(), affair, idSet);
    }

    /**
     * 修改备忘录事件
     *
     * @param userDetails
     * @param affair
     * @return
     * @author linqin
     * @date 2019/1/7 16:38
     */
    @PostMapping("modify_affair")
    public Response modifyAffair(@AuthenticationPrincipal UserDetails userDetails, MemoAffair affair) {
        if (affair.getId() == null) {
            return ResponseFactory.errMissingParameter();
        }
        // 判断时间，不能小于现在
        if (affair.getTargetTime() != null && affair.getTargetTime().getTime() < System.currentTimeMillis()) {
            return ResponseFactory.err("时间不能晚于当前时间!");
        }
        HashSet<Integer> idSet = null;
        if (StringUtils.isNotBlank(affair.getUsers())) {
            // 取出用户id
            idSet = new HashSet<>();
            if (Utils.getIds(affair.getUsers(), idSet)) {
                return ResponseFactory.err("用户id错误!");
            }
        }
        return memoAffairService.modifyAffair(userDetails.getUserId(), affair, idSet);
    }


    /**
     * 获取活动列表
     *
     * @param userDetails
     * @param start
     * @param end
     * @return
     * @author linqin
     * @date 2019/1/7 16:38
     */
    @PostMapping("list")
    public Response getAffairList(@AuthenticationPrincipal UserDetails userDetails, Long start, Long end) {
        if (start == null) {
            start = 0L;
        }
        if (end == null) {
            end = 0L;
        }
        return memoAffairService.getAffairList(userDetails.getUserId(), start, end);
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
        return memoAffairService.getListForFriend(userDetails.getUserId(), start, end, friendUserId);
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
        if (id == null ) {
            return ResponseFactory.errMissingParameter();
        }
        return memoAffairService.delMemo(userDetails.getUserId(), id);
    }

    /**
     * 首页的三个备忘录
     *
     * @param userDetails 用户信息
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @PostMapping("home")
    public Response getHomeList(@AuthenticationPrincipal UserDetails userDetails) {
        return memoAffairService.getHomeList(userDetails.getUserId());
    }


}
