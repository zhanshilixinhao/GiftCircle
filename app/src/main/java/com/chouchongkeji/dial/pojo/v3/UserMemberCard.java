package com.chouchongkeji.dial.pojo.v3;

import java.math.BigDecimal;
import java.util.Date;

public class UserMemberCard {
    private Integer id;

    private Integer membershipCardId;

    private Integer userId;

    private BigDecimal balance;

    private BigDecimal totalAmount;

    private BigDecimal consumeAmount;

    private Date updated;

    private Date created;

    private Byte status;

    public UserMemberCard(Integer id, Integer membershipCardId, Integer userId, BigDecimal balance, BigDecimal totalAmount, BigDecimal consumeAmount, Date updated, Date created,Byte status) {
        this.id = id;
        this.membershipCardId = membershipCardId;
        this.userId = userId;
        this.balance = balance;
        this.totalAmount = totalAmount;
        this.consumeAmount = consumeAmount;
        this.updated = updated;
        this.created = created;
        this.status = status;
    }

    public UserMemberCard() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMembershipCardId() {
        return membershipCardId;
    }

    public void setMembershipCardId(Integer membershipCardId) {
        this.membershipCardId = membershipCardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
