package com.chouchongkeji.dial.pojo.v3;

import java.math.BigDecimal;
import java.util.Date;

public class TransferSendExpense {
    private Integer id;

    private Integer transferSendId;

    private Integer storeMemberId;

    private BigDecimal sendMoney;

    private Date updated;

    private Date created;

    public TransferSendExpense(Integer id, Integer transferSendId, Integer storeMemberId, BigDecimal sendMoney, Date updated, Date created) {
        this.id = id;
        this.transferSendId = transferSendId;
        this.storeMemberId = storeMemberId;
        this.sendMoney = sendMoney;
        this.updated = updated;
        this.created = created;
    }

    public TransferSendExpense() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransferSendId() {
        return transferSendId;
    }

    public void setTransferSendId(Integer transferSendId) {
        this.transferSendId = transferSendId;
    }

    public Integer getStoreMemberId() {
        return storeMemberId;
    }

    public void setStoreMemberId(Integer storeMemberId) {
        this.storeMemberId = storeMemberId;
    }

    public BigDecimal getSendMoney() {
        return sendMoney;
    }

    public void setSendMoney(BigDecimal sendMoney) {
        this.sendMoney = sendMoney;
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