package com.chouchongkeji.service.iwant.receiveAddress;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.pojo.iwant.receiveAddress.Shipping;

/**
 * @author yy
 * @date 2018/6/5
 **/
public interface ReceiveAddressService {
    /**
     * 获得用户地址列表
     *
     * @param: [userId 用户id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    Response getReceiveAddressList(Integer userId);

    /**
     * 删除收货地址
     *
     * @param: [userId 用户id, id 收货地址id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    Response delReceiveAddress(Integer userId, Integer id);

    /**
     * 设置地址为默认地址
     *
     * @param: [userId 用户id, id 收货地址id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    Response setDefaultReceiveAddress(Integer userId, Integer id);

    /**
     * 添加收货地址
     *
     * @param: [userId 用户id, shipping 收货地址信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    Response addReceiveAddress(Integer userId, Shipping shipping);
}
