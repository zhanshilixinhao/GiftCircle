package com.chouchongkeji.service.v3.vo;

import com.yichen.auth.jackson.ImgUrl;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2019/10/28
 */

public class ChargeDetailVo extends ChargeListVo {

    private Integer storeId;

    private String storeName;

    private String address;

    private String phone;

    @ImgUrl
    private String logo;

    /**
     * 商品名称
     */
    private String title;

    private Long orderNo;
    /**
     * 充值前金额
     */
    private BigDecimal beforeMoney;
    /**
     * 充值后金额
     */
    private BigDecimal laterMoney;

    public BigDecimal getBeforeMoney() {
        return beforeMoney;
    }

    public void setBeforeMoney(BigDecimal beforeMoney) {
        this.beforeMoney = beforeMoney;
    }

    public BigDecimal getLaterMoney() {
        return laterMoney;
    }

    public void setLaterMoney(BigDecimal laterMoney) {
        this.laterMoney = laterMoney;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
}
