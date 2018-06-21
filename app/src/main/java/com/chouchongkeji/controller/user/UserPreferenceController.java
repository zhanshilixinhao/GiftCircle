package com.chouchongkeji.controller.user;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.user.UserPreferenceService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Response addTag(@AuthenticationPrincipal UserDetails userDetails, Integer friendUserId, Integer tagId) {
        if (friendUserId == null || tagId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return userPreferenceService.addTag(userDetails.getUserId(), friendUserId, tagId);
    }

}
