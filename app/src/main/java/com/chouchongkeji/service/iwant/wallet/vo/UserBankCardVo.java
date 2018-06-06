package com.chouchongkeji.service.iwant.wallet.vo;

import java.util.Date;

/**
 * @author yy
 * @date 2018/6/6
 **/
public class UserBankCardVo {
    // 用户银行卡id
    private Integer id;
    // 银行id
    private Integer bankId;
    // 银行logo
    private String logo;
    // 银行名称
    private String bankName;
    // 开户行名称
    private String depositBank;
    // 持卡人姓名
    private String cardHolder;
    // 银行卡号
    private String cardNo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
