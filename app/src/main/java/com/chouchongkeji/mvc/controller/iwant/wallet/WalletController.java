package com.chouchongkeji.mvc.controller.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.iwant.wallet.WalletService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yy
 * @date 2018/6/5
 **/

@RestController
@RequestMapping("auth/v1/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    /**
     * 钱包详情
     *
     * @param userDetails
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @PostMapping("detail")
    public Response walletDetail(@AuthenticationPrincipal UserDetails userDetails) {
        return walletService.getWalletDetail(userDetails.getUserId());
    }



}
