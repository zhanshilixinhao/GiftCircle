package com.chouchongkeji.mvc.controller.iwant.wallet;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.iwant.wallet.WalletService;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    /**
     * 钱包收益记录
     *
     * @param userDetails
     * @param starting    开始时间
     * @param ending      截至时间
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @PostMapping("earn_record")
    public Response earnRecordList(@AuthenticationPrincipal UserDetails userDetails, PageQuery pageQuery,
                                   Long starting, Long ending) throws ParseException {

        if (starting != null) {
            Date now = new Date(starting);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String format = dateFormat.format(now);//日期
            Date parse = dateFormat.parse(format);  //时间戳
             starting = parse.getTime() / 1000;
        }
        if (ending != null){
            Date now = new Date(ending);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String format = dateFormat.format(now);
            Date parse = dateFormat.parse(format);
            ending = DateUtils.addDays(parse, 1).getTime()/1000;
        }
        return walletService.earnRecordList(userDetails.getUserId(), pageQuery, starting, ending);
    }


}
