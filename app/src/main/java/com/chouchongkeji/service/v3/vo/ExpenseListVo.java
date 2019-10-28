package com.chouchongkeji.service.v3.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linqin
 * @date 2019/10/25
 */

public class ExpenseListVo {

    private Integer id;

    private Integer membershipCardId;

    private Integer userId;

    private BigDecimal expenseMoney;

    private Byte type;

    private String explain;

    private Date created;

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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public BigDecimal getExpenseMoney() {
        return expenseMoney;
    }

    public void setExpenseMoney(BigDecimal expenseMoney) {
        this.expenseMoney = expenseMoney;
    }
}
