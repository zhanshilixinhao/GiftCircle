package com.chouchongkeji.service.v3.vo;

/**
 * @author linqin
 * @date 2019/10/28
 */

public class ChargeDetailVo extends ChargeListVo {

    private Integer storeId;

    private String storeName;

    private String address;

    private String phone;

    private Integer targetId;
    /**
     * 商品名称
     */
    private String title;

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

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
