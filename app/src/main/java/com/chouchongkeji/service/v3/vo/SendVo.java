package com.chouchongkeji.service.v3.vo;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2019/12/2
 */

public class SendVo {

    private String title;

    private BigDecimal sendMoney;

    private String summary;

    private Byte status;

    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getSendMoney() {
        return sendMoney;
    }

    public void setSendMoney(BigDecimal sendMoney) {
        this.sendMoney = sendMoney;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
