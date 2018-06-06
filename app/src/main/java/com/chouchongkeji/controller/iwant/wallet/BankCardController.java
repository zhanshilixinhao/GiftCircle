package com.chouchongkeji.controller.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.iwant.wallet.BankCardService;
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
@RequestMapping("auth/v1/bankCard")
public class BankCardController {
    @Autowired
    private BankCardService bankCardService;

    /**
     * 获得银行卡类型列表
     *
     * @param: [details 用户认证信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/5
     */
    @PostMapping("bankList")
    public Response getBankList(@AuthenticationPrincipal UserDetails details) {
        return bankCardService.getBankList();
    }

    /**
     * 获得用户银行卡集合
     *
     * @param: [details 用户认证信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/5
     */
    @PostMapping("list")
    public Response getUserBankCardList(@AuthenticationPrincipal UserDetails details) {
        return bankCardService.getUserBankCardList(details.getUserId());
    }
}
