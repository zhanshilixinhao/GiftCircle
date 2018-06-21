package com.chouchongkeji.dial.dao.iwant.receiveAddress;

import com.chouchongkeji.dial.pojo.iwant.receiveAddress.Shipping;
import com.chouchongkeji.service.iwant.receiveAddress.vo.ShippingListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    /**
     * 获得收货地址列表
     *
     * @param: [userId 用户id]
     * @return: java.util.List<com.chouchongkeji.service.iwant.receiveAddress.vo.ShippingListVo>
     * @author: yy
     * @Date: 2018/6/6
     */
    List<ShippingListVo> selectListByUserId(@Param("userId") Integer userId);

    /**
     * 获得当前用户的默认地址
     *
     * @param: [userId 用户id]
     * @return: java.util.List<com.chouchongkeji.dial.pojo.iwant.receiveAddress.Shipping>
     * @author: yy
     * @Date: 2018/6/6
     */
    List<Shipping> selectByDefaultStatus(@Param("userId") Integer userId);
}