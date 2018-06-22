package com.chouchongkeji.controller.user;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.user.UserPreferenceService;
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
 * @date 2018/6/21
 */

@RestController
@RequestMapping("auth/user/")
public class UserPreferenceController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    /**
     * 获取标签列表
     *
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("tag/list")
    public Response getTags() {
        return userPreferenceService.getTags();
    }


    /**
     * 添加标签
     *
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("tag/add")
    public Response addTag(@AuthenticationPrincipal UserDetails userDetails, Integer friendUserId, String tagIds) {
        if (friendUserId == null || StringUtils.isBlank(tagIds)) {
            return ResponseFactory.errMissingParameter();
        }
        String[] strings = tagIds.split(",");
        HashSet<Integer> ids = new HashSet<>();
        if (getIds(strings, ids)) return ResponseFactory.err("参数错误，请检查!");
        return userPreferenceService.addTag(userDetails.getUserId(), friendUserId, ids);
    }

    /**
     * 获取用户礼物偏好列表
     *
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("gift/preference/list")
    public Response getGiftPreference(@AuthenticationPrincipal UserDetails userDetails, Integer targetUserId) {
        if (targetUserId == null) {
            targetUserId = userDetails.getUserId();
        }
        return userPreferenceService.getGiftPreference(userDetails.getUserId(), targetUserId);
    }


    /**
     * 添加礼物偏好
     *
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @PostMapping("gift/preference/modify")
    public Response addGiftPreference(@AuthenticationPrincipal UserDetails userDetails, String ids) {
        if (StringUtils.isBlank(ids)) {
            return ResponseFactory.errMissingParameter();
        }
        String[] strings = ids.split(",");
        HashSet<Integer> idSet = new HashSet<>();
        if (getIds(strings, idSet)) return ResponseFactory.err("参数错误，请检查!");
        return userPreferenceService.addGiftPreference(userDetails.getUserId(), idSet);
    }

    private boolean getIds(String[] strings, HashSet<Integer> idSet) {
        try {
            Integer id = null;
            for (String idStr : strings) {
                id = Integer.parseInt(idStr);
                idSet.add(id);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
