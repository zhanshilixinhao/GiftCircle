package com.chouchongkeji.mvc.controller.backpack.base;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.base.BpService;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yichenshanren
 * @date 2018/7/2
 */

@RequestMapping("auth/v1/bp")
@RestController
public class BpController {

    @Autowired
    private BpService bpService;

    /**
     * 背包列表
     *
     * @param userDetails 用户信息
     * @param type        1 物品 2 虚拟物品 3 优惠券
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @PostMapping("list")
    public Response getList(@AuthenticationPrincipal UserDetails userDetails, Integer type, PageQuery page) {
        if (type == null || type < 0 || type > 3) {
            return ResponseFactory.err("type错误!");
        }
        return bpService.getList(userDetails.getUserId(), type, page);
    }


    /**
     * 微信背包列表
     *
     * @param userDetails 用户信息
     * @param type        1 物品 2 虚拟物品 3 优惠券
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @PostMapping("wx_list")
    public Response getWxBpList(@AuthenticationPrincipal UserDetails userDetails, Integer type) {
        if (type == null || type < 0 || type > 3) {
            return ResponseFactory.err("type错误!");
        }
        return bpService.getWxBpList(userDetails.getUserId(), type);
    }


    /**
     * 我的背包红点(未查看的新品)
     *
     * @param userDetails
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @PostMapping("unread")
    public Response getUnreadBpItem(@AuthenticationPrincipal UserDetails userDetails) {
            return bpService.getUnreadBpItem(userDetails.getUserId());
    }


    /**
     * 背包搜索
     *
     * @param userDetails 用户信息
     * @param key         关键字
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @PostMapping("search")
    public Response search(@AuthenticationPrincipal UserDetails userDetails, String key, PageQuery page) {
        if (StringUtils.isBlank(key)) {
            return ResponseFactory.errMissingParameter();
        }
        return bpService.search(userDetails.getUserId(), key, page);
    }


}
