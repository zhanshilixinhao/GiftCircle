package com.chouchongkeji.mvc.controller.iwant.wallet;

import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.iwant.wallet.BankCardService;
import com.chouchongkeji.service.iwant.wallet.vo.UserBankCardVo;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 删除用户银行卡
     *
     * @param: [details 用户认证信息, id 银行卡id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    @PostMapping("del")
    public Response delUserBankCard(@AuthenticationPrincipal UserDetails details, Integer id) {
        if (id == null) {
            return ResponseFactory.errMissingParameter();
        }
        return bankCardService.delUserBankCard(details.getUserId(), id);
    }

    /**
     * 添加用户银行卡
     *
     * @param: [details 用户详情, userBankCardVo 银行卡信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/7
     */
    @PostMapping("add")
    public Response addUserBankCard(@AuthenticationPrincipal UserDetails details, UserBankCardVo userBankCardVo) {
        //校验必传参数
        if (userBankCardVo == null) {
            return ResponseFactory.errMissingParameter();
        } else {
            if (userBankCardVo.getBankId() == null) {
                return ResponseFactory.errMissingParameter();
            }
            if (StringUtils.isAnyBlank(userBankCardVo.getCardHolder(), userBankCardVo.getCardNo(),
                    userBankCardVo.getDepositBank())) {
                return ResponseFactory.errMissingParameter();
            }
        }
        return bankCardService.addUserBankCard(details.getUserId(), userBankCardVo);
    }
}
