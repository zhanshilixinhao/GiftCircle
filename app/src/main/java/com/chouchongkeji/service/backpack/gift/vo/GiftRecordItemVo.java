package com.chouchongkeji.service.backpack.gift.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yichenshanren
 * @date 2018/7/9
 */

public class GiftRecordItemVo {

    private Integer recordId;
    private Integer inoutType;
    private BigDecimal amount;
    private String event;
    private String greetting;
    private String obType;
    private Date created;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getInoutType() {
        return inoutType;
    }

    public void setInoutType(Integer inoutType) {
        this.inoutType = inoutType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getGreetting() {
        return greetting;
    }

    public void setGreetting(String greetting) {
        this.greetting = greetting;
    }

    public String getObType() {
        return obType;
    }

    public void setObType(String obType) {
        this.obType = obType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
