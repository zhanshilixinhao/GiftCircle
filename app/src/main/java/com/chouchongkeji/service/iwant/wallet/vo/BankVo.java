package com.chouchongkeji.service.iwant.wallet.vo;

/**
 * 用于绑定银行卡的银行列表显示
 *
 * @author yy
 * @date 2018/6/5
 **/
public class BankVo {
    // 银行id
    private Integer bankId;
    // 银行名称
    private String bankName;
    // 银行logo
    private String logo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
}
