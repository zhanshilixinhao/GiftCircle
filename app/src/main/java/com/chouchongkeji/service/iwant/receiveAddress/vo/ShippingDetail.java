package com.chouchongkeji.service.iwant.receiveAddress.vo;

/**
 * @author yy
 * @date 2018/6/6
 **/
public class ShippingDetail {
    // 用户地址id
    private Integer id;
    // 收货人名字
    private String consigneeName;
    // 收货人电话
    private String phone;
    // 收货人地址详情
    private String addressDetail;
    // 所在地区
    private String address;
    // 所在地区编码
    private Integer adcode;

    public Integer getAdcode() {
        return adcode;
    }

    public void setAdcode(Integer adcode) {
        this.adcode = adcode;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
