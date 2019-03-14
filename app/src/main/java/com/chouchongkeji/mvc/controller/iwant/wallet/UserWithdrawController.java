package com.chouchongkeji.mvc.controller.iwant.wallet;

import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.iwant.wallet.UserWithdrawService;
import com.chouchongkeji.service.iwant.wallet.vo.UserBankCardVo;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
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
        if (amount.compareTo(new BigDecimal(0)) < 0) {
            return ResponseFactory.err("提现金额必须大于0");
        }
        return userWithdrawService.addUserWithdraw(details.getUserId(), id, amount);
    }


    /**
     * 小程序背包物品折现
     *
     * @param: [details 用户认证信息, bpId 背包id, bankCard 银行信息 type 1 支付宝，2 银行卡]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/8
     */
    @PostMapping("add_wx")
    public Response addWXBpWithdraw(@AuthenticationPrincipal UserDetails details, Long bpId, Byte type, UserBankCardVo bankCard) {
        if (bpId == null || bankCard == null || type == null) {
            return ResponseFactory.errMissingParameter();
        }
        if (StringUtils.isAnyBlank(bankCard.getCardHolder(), bankCard.getCardNo()) ||
                (type == 2 && bankCard.getBankId() == null && StringUtils.isBlank( bankCard.getDepositBank()))) {
            return ResponseFactory.errMissingParameter();
        }
        if (type != 1 && type != 2) {
            return ResponseFactory.errMissingParameter();

        }
        return userWithdrawService.addWXBpWithdraw(details.getUserId(), bpId, type, bankCard);
    }

    /**
     * 微信折现 记录
     * @param details
     * @return
     */
    @PostMapping("wxRecords")
    public Response addWXBpWithdraw(@AuthenticationPrincipal UserDetails details) {
        return userWithdrawService.getWxRecords(details.getUserId());
    }


    /**
     * 提现记录
     *
     * @param: [details 用户认证消息, pageQuery 分页信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/12
     */
    @PostMapping("list")
    public Response getUserWithdrawList(@AuthenticationPrincipal UserDetails details, PageQuery pageQuery) {
        return userWithdrawService.getUserWithdrawList(details.getUserId(), pageQuery);
    }


}
