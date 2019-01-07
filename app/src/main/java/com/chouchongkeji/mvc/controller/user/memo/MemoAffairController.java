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
            if (affair.getCount() != idSet.size()) {
                return ResponseFactory.err("人数和邀请的用户数不一致!");
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
            if (affair.getCount() != idSet.size()) {
                return ResponseFactory.err("人数和邀请的用户数不一致!");
            }
        }
        return memoAffairService.modifyAffair(userDetails.getUserId(), affair, idSet);
    }

}
