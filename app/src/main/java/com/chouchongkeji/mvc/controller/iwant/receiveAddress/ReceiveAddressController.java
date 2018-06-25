package com.chouchongkeji.mvc.controller.iwant.receiveAddress;

import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.dial.pojo.iwant.receiveAddress.Shipping;
import com.chouchongkeji.service.iwant.receiveAddress.ReceiveAddressService;
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
@RequestMapping("auth/v1/reAddress")
public class ReceiveAddressController {
    @Autowired
    private ReceiveAddressService receiveAddressService;

    /**
     * 获得收货地址列表
     *
     * @param: [details 用户认证信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    @PostMapping("list")
    public Response getReceiveAddressList(@AuthenticationPrincipal UserDetails details) {
        return receiveAddressService.getReceiveAddressList(details.getUserId());
    }

    /**
     * 删除用户地址
     *
     * @param: [details 用户认证信息, id 收货地址id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    @PostMapping("del")
    public Response delReceiveAddress(@AuthenticationPrincipal UserDetails details, Integer id) {
        //校验必传参数
        if (id == null) {
            return ResponseFactory.errMissingParameter();
        }
        return receiveAddressService.delReceiveAddress(details.getUserId(), id);
    }

    /**
     * 设置默认的收货地址
     *
     * @param: [details 用户信息, id 收货地址id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    @PostMapping("setDefault")
    public Response setDefaultReceiveAddress(@AuthenticationPrincipal UserDetails details, Integer id) {
        //校验必传参数
        if (id == null) {
            return ResponseFactory.errMissingParameter();
        }
        return receiveAddressService.setDefaultReceiveAddress(details.getUserId(), id);
    }

    /**
     * 添加收货地址
     *
     * @param: [details 用户认证信息, shipping 收货地址信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    @PostMapping("add")
    public Response addReceiveAddress(@AuthenticationPrincipal UserDetails details, Shipping shipping) {
        //校验必传参数
        if (shipping == null) {
            return ResponseFactory.errMissingParameter();
        } else {
            if (shipping.getAdcode() == null) {
                return ResponseFactory.errMissingParameter();
            }
            if (StringUtils.isAnyBlank(shipping.getAddress(), shipping.getPhone(),
                    shipping.getConsigneeName(), shipping.getAddressDetail())) {
                return ResponseFactory.errMissingParameter();
            }
        }
        return receiveAddressService.addReceiveAddress(details.getUserId(), shipping);
    }

    /**
     * 获得收货地址详情
     *
     * @param: [details 用户认证信息, id 收货地址id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    @PostMapping("detail")
    public Response getReceiveAddressDetail(@AuthenticationPrincipal UserDetails details, Integer id) {
        return receiveAddressService.getReceiveAddressDetail(details.getUserId(), id);
    }

    /**
     *
     *
     * @param: [details 用户认证信息, shipping ]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    @PostMapping("update")
    public Response updateReceiveAddress(@AuthenticationPrincipal UserDetails details, Shipping shipping) {
        if (shipping == null) {
            return ResponseFactory.errMissingParameter();
        } else {
            if (shipping.getAdcode() == null || shipping.getId() == null) {
                return ResponseFactory.errMissingParameter();
            }
            if (StringUtils.isAnyBlank(shipping.getAddress(), shipping.getPhone(),
                    shipping.getConsigneeName(), shipping.getAddressDetail())) {
                return ResponseFactory.errMissingParameter();
            }
        }
        return receiveAddressService.updateReceiveAddress(details.getUserId(), shipping);
    }
}
