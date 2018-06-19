package com.yichen.auth.controller;

import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.yichen.auth.service.SmsCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yichenshanren
 * @date 2017/11/26
 */
@RestController
public class SmsCodeController {

    @Autowired
    private SmsCodeService smsCodeService;

    @PostMapping("ask/code")
    public Response createSmsCode(String phone, Integer type) {
        if (StringUtils.isBlank(phone) || type == null) {
            return ResponseFactory.errMissingParameter();
        }
        return smsCodeService.sendSmsCode(phone, type);
    }

    @PostMapping("verify/code")
    public Response verifyCode(String phone, Integer type, String code) {
        if (StringUtils.isAnyBlank(phone, code) || type == null) {
            return ResponseFactory.errMissingParameter();
        }
        return smsCodeService.verifySmsCode(phone, type, code);
    }

    @RequestMapping("/oauth/error")
    public Response oauthError(Exception e) {
        return ResponseFactory.err(e.getMessage());
    }

}
