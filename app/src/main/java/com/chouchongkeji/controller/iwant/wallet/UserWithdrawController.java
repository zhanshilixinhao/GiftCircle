package com.chouchongkeji.controller.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.iwant.wallet.UserWithdrawService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author yy
 * @date 2018/6/5
 **/

@RestController
@RequestMapping("auth/v1/withdraw")
public class UserWithdrawController {
    @Autowired
    private UserWithdrawService userWithdrawService;


    /**
     * 添加用户提现记录
     *
     * @param: [details 用户认证信息, id 用户银行卡id, amount ]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/8
     */
    @PostMapping("add")
    public Response addUserWithdraw(@AuthenticationPrincipal UserDetails details, Integer id, BigDecimal amount) {
        if (id == null || amount == null) {
            return ResponseFactory.errMissingParameter();
        }
        if (amount.compareTo(new BigDecimal(0)) != 1) {
            return ResponseFactory.err("提现金额必须大于0");
        }
        return userWithdrawService.addUserWithdraw(details.getUserId(), id, amount);
    }
}
