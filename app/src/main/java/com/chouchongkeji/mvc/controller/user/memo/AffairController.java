package com.chouchongkeji.mvc.controller.user.memo;

import com.alibaba.druid.mock.MockArray;
import com.chouchongkeji.dial.pojo.user.memo.MemoAffair;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.Utils;
import com.chouchongkeji.service.user.memo.AffairService;
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
 * @date 2019/3/11 16:19
 */
@RestController
@RequestMapping("auth/memo/affair2")
public class AffairController {

    @Autowired
    private AffairService affairService;

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
    public Response addAffair(@AuthenticationPrincipal UserDetails userDetails, MemoAffair memoAffair) {
        if (StringUtils.isAnyBlank(memoAffair.getDetail()) || memoAffair.getTargetTime() == null) {
            return ResponseFactory.errMissingParameter();
        }
        if (memoAffair.getIsCirculation() == null){
            memoAffair.setIsCirculation((byte)0);
        }
        HashSet<Integer> idSet = null;
        if (StringUtils.isNotBlank(memoAffair.getUsers())) {
            // 取出用户id
            idSet = new HashSet<>();
            if (Utils.getIds(memoAffair.getUsers(), idSet)) {
                return ResponseFactory.err("用户id错误!");
            }
        }
        return affairService.addAffair(userDetails.getUserId(),memoAffair,idSet);
    }


}
