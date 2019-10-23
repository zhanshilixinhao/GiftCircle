package com.chouchongkeji.dial.pojo.v3;

import java.util.Date;

public class Store {
    private Integer id;

    private Integer merchantId;

    private String name;

    private String address;

    private String phone;

    private Date created;

    private Date updated;

    public Store(Integer id, Integer merchantId, String name, String address, String phone, Date created, Date updated) {
        this.id = id;
        this.merchantId = merchantId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.created = created;
        this.updated = updated;
    }

    public Store() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}