package com.chouchongkeji.controller.iwant.merchant;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.iwant.merchant.MerchantService;
import com.chouchongkeji.service.iwant.merchant.vo.MerchantVo;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yy
 * @date 2018/6/20
 **/

@RestController
@RequestMapping("auth/v1/merchant")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;


    /**
     * 商家认证申请
     *
     * @param: [details 用户认证信息, merchantVo 商家信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    @PostMapping("apply")
    public Response applyMerchant(@AuthenticationPrincipal UserDetails details, MerchantVo merchantVo) {
        if (StringUtils.isAnyBlank(merchantVo.getPhone(), merchantVo.getAddress(),
                merchantVo.getRegistrationNo(), merchantVo.getName(),merchantVo.getLicensePic())) {
            return ResponseFactory.errMissingParameter();
        }
        return merchantService.applyMerchant(details.getUserId(), merchantVo);
    }

    /**
     * 获得该用户的商家认证状态
     *
     * @param: [details 用户认证信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    @PostMapping("status")
    public Response getMerchantStatus(@AuthenticationPrincipal UserDetails details) {
        return merchantService.getMerchantStatus(details.getUserId());
    }
}
