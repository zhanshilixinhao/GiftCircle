package com.chouchongkeji.service.backpack.consignment.vo;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2018/7/2
 */
public class PriceVo {
    private BigDecimal newset;

    private BigDecimal hight;

    private BigDecimal low;

    public BigDecimal getNewset() {
        return newset;
    }

    public void setNewset(BigDecimal newset) {
        this.newset = newset;
    }

    public BigDecimal getHight() {
        return hight;
    }

    public void setHight(BigDecimal hight) {
        this.hight = hight;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }
}
