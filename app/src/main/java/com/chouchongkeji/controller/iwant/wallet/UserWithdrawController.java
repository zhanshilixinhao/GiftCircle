package com.chouchongkeji.controller.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.iwant.wallet.UserWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yy
 * @date 2018/6/5
 **/

@RestController
@RequestMapping("auth/v1/withdraw")
public class UserWithdrawController {
    @Autowired
    private UserWithdrawService userWithdrawService;
}
