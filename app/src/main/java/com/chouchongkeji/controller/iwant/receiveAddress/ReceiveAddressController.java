package com.chouchongkeji.controller.iwant.receiveAddress;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.iwant.receiveAddress.ReceiveAddressService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yy
 * @date 2018/6/5
 **/
@RestController
@RequestMapping("auth/v1/reAddress")
public class ReceiveAddressController {
    @Autowired
    private ReceiveAddressService receiveAddressService;

//    public Response getReceiveAddressList(@AuthenticationPrincipal UserDetails details) {
//
//    }
}
