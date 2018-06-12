package com.chouchongkeji.service.iwant.wallet.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户提现记录
 *
 * @author yy
 * @date 2018/6/12
 **/
public class UserWithdrawVo {
    // 提现金额
    private BigDecimal amount;
    // 提现状态，1-申请提现，2-提现中，3-提现成功，4-提现失败
    private Integer status;
    // 提现时间
    private Date created;
    // 提现说明
    private String describe;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
