package com.chouchongkeji.dial.pojo.user;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentInfo {
    private Integer id;

    private Integer userId;

    private Byte type;

    private Long orderNo;

    private BigDecimal totalFee;

    private Integer payPlatform;

    private String platformNumber;

    private String platformStatus;

    private String seller;

    private String buyer;

    private Date created;

    private Date updated;

    public PaymentInfo(Integer id, Integer userId, Byte type, Long orderNo, BigDecimal totalFee, Integer payPlatform, String platformNumber, String platformStatus, String seller, String buyer, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.orderNo = orderNo;
        this.totalFee = totalFee;
        this.payPlatform = payPlatform;
        this.platformNumber = platformNumber;
        this.platformStatus = platformStatus;
        this.seller = seller;
        this.buyer = buyer;
        this.created = created;
        this.updated = updated;
    }

    public PaymentInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getPayPlatform() {
        return payPlatform;
    }

    public void setPayPlatform(Integer payPlatform) {
        this.payPlatform = payPlatform;
    }

    public String getPlatformNumber() {
        return platformNumber;
    }

    public void setPlatformNumber(String platformNumber) {
        this.platformNumber = platformNumber == null ? null : platformNumber.trim();
    }

    public String getPlatformStatus() {
        return platformStatus;
    }

    public void setPlatformStatus(String platformStatus) {
        this.platformStatus = platformStatus == null ? null : platformStatus.trim();
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller == null ? null : seller.trim();
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer == null ? null : buyer.trim();
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