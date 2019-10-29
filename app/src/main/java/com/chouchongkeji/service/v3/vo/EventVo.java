package com.chouchongkeji.service.v3.vo;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2019/10/29
 */

public class EventVo {
    private Integer id;

    private BigDecimal rechargeMoney;

    private BigDecimal sendMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public BigDecimal getSendMoney() {
        return sendMoney;
    }

    public void setSendMoney(BigDecimal sendMoney) {
        this.sendMoney = sendMoney;
    }
}
