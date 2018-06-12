package com.chouchongkeji.service.iwant.receiveAddress.impl;

import com.chouchongkeji.dao.iwant.receiveAddress.ShippingMapper;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.pojo.iwant.receiveAddress.Shipping;
import com.chouchongkeji.service.iwant.receiveAddress.ReceiveAddressService;
import com.chouchongkeji.service.iwant.receiveAddress.vo.ShippingDetail;
import com.chouchongkeji.service.iwant.receiveAddress.vo.ShippingListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author yy
 * @date 2018/6/5
 **/

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class ReceiveAddressServiceImpl implements ReceiveAddressService {
    @Autowired
    private ShippingMapper shippingMapper;

    /**
     * 获得用户的收货地址列表
     *
     * @param: [userId 用户id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    @Override
    public Response getReceiveAddressList(Integer userId) {
        List<ShippingListVo> shippingListVos = shippingMapper.selectListByUserId(userId);
        return ResponseFactory.sucData(shippingListVos);
    }

    /**
     * 删除收货地址
     *
     * @param: [userId 用户id, id 收货地址id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    @Override
    public Response delReceiveAddress(Integer userId, Integer id) {
        Shipping shipping = shippingMapper.selectByPrimaryKey(id);
        if (shipping != null) {
            shipping.setStatus((byte)3);
            shippingMapper.updateByPrimaryKey(shipping);
            return ResponseFactory.sucMsg("删除成功");
        } else {
            return ResponseFactory.err("无效的地址");
        }
    }

    /**
     * 设置默认收货地址
     *
     * @param: [userId 用户id, id 收货地址id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    @Override
    public Response setDefaultReceiveAddress(Integer userId, Integer id) {
        // 获得用户当前的默认地址
        List<Shipping> shippings = shippingMapper.selectByDefaultStatus(userId);
        // 如果有默认地址，将默认地址设为正常状态，再将当前地址设为默认地址
        if (shippings.size() > 0) {
            for (Shipping shipping: shippings) {
                shipping.setStatus((byte)1);
                shippingMapper.updateByPrimaryKey(shipping);
            }
            Shipping shipping = shippingMapper.selectByPrimaryKey(id);
            if (shipping != null) {
                shipping.setStatus((byte)2);
                shippingMapper.updateByPrimaryKey(shipping);
            } else {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "无效的地址");
            }
        } else { // 如果没有默认地址，直接将该地址设为默认地址
            Shipping shipping = shippingMapper.selectByPrimaryKey(id);
            if (shipping != null) {
                shipping.setStatus((byte)2);
                shippingMapper.updateByPrimaryKey(shipping);
            } else {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "无效的地址");
            }
        }
        return ResponseFactory.sucMsg("设置成功");
    }

    /**
     * 添加收货地址
     *
     * @param: [userId 用户id, shipping 收货地址信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    @Override
    public Response addReceiveAddress(Integer userId, Shipping shipping) {
        // 设置其它字段信息
        shipping.setStatus((byte)1);
        shipping.setCreated(new Date());
        shipping.setUpdated(new Date());
        shipping.setUserId(userId);
        // 向数据库添加收货地址
        int count = shippingMapper.insert(shipping);
        if (count == 1) {
            return ResponseFactory.sucMsg("添加成功");
        } else {
            return ResponseFactory.err("添加失败");
        }
    }

    /**
     * 获得收货地址详情
     *
     * @param: [userId 用户id, id 收货地址id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    @Override
    public Response getReceiveAddressDetail(Integer userId, Integer id) {
        Shipping shipping = shippingMapper.selectByPrimaryKey(id);
        ShippingDetail shippingDetail = new ShippingDetail();
        if (shipping != null) {
            shippingDetail.setId(shipping.getId());
            shippingDetail.setAddress(shipping.getAddress());
            shippingDetail.setPhone(shipping.getPhone());
            shippingDetail.setConsigneeName(shipping.getConsigneeName());
            shippingDetail.setAddressDetail(shipping.getAddressDetail());
            shippingDetail.setAdcode(shipping.getAdcode());
        }  else {
            return ResponseFactory.err("无效的地址");
        }
        return ResponseFactory.sucData(shippingDetail);
    }

    /**
     * 修改用户收货地址
     *
     * @param: [userId 用户id, shipping 收货地址信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    @Override
    public Response updateReceiveAddress(Integer userId, Shipping shippingOld) {
        Shipping shipping = shippingMapper.selectByPrimaryKey(shippingOld.getId());
        if (shipping != null) {
            shipping.setAdcode(shippingOld.getAdcode());
            shipping.setPhone(shippingOld.getPhone());
            shipping.setConsigneeName(shippingOld.getConsigneeName());
            shipping.setAddressDetail(shippingOld.getAddressDetail());
            shipping.setAddress(shippingOld.getAddress());
            shipping.setUpdated(new Date());
            shippingMapper.updateByPrimaryKey(shipping);
        } else {
            return ResponseFactory.err("修改失败");
        }
        return ResponseFactory.sucMsg("修改成功");
    }
}
