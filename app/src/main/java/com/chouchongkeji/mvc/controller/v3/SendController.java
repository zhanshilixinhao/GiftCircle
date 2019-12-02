package com.chouchongkeji.mvc.controller.v3;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.v3.SendService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2019/12/2
 */
@RestController
@RequestMapping("auth/v3/cardSend")
public class SendController {

    @Autowired
    private SendService sendService;

    /**
     * 会员卡余额赠送
     * @param userDetails 用户(赠送者)
     * @param cardId 卡片id
     * @param sendMoney 赠送金额
     * @return
     */
    @PostMapping("wx")
    public Response cardSend(@AuthenticationPrincipal UserDetails userDetails, Integer cardId, BigDecimal sendMoney){
        if (cardId == null || sendMoney == null){
            return ResponseFactory.errMissingParameter();
        }
        if (sendMoney.compareTo(new BigDecimal(0)) <= 0){
            return ResponseFactory.err("请输入正确的金额");
        }
        return sendService.cardSend(userDetails,cardId,sendMoney);
    }

}
