package com.chouchongkeji.service.iwant.receiveAddress.vo;

import java.util.Date;

/**
 * @author yy
 * @date 2018/6/6
 **/
public class ShippingListVo {
    // 用户地址id
    private Integer id;
    // 收货人名字
    private String consigneeName;
    // 收货人电话
    private String phone;
    // 收货人地址详情
    private String addressDetail;
    // 地址状态 1.不是默认地址 2.是默认地址
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
