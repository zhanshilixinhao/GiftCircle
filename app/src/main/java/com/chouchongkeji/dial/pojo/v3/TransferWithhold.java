package com.chouchongkeji.dial.pojo.v3;

import java.math.BigDecimal;
import java.util.Date;

public class TransferWithhold {
    private Integer transferSendId;

    private Integer userId;

    private BigDecimal sendMoney;

    private Byte status;

    private Date updated;

    private Date created;

    public TransferWithhold(Integer transferSendId, Integer userId, BigDecimal sendMoney, Byte status, Date updated, Date created) {
        this.transferSendId = transferSendId;
        this.userId = userId;
        this.sendMoney = sendMoney;
        this.status = status;
        this.updated = updated;
        this.created = created;
    }

    public TransferWithhold() {
        super();
    }

    public Integer getTransferSendId() {
        return transferSendId;
    }

    public void setTransferSendId(Integer transferSendId) {
        this.transferSendId = transferSendId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getSendMoney() {
        return sendMoney;
    }

    public void setSendMoney(BigDecimal sendMoney) {
        this.sendMoney = sendMoney;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}