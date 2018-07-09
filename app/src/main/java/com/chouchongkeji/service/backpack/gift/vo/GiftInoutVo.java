package com.chouchongkeji.service.backpack.gift.vo;

import java.math.BigDecimal;

/**
 * @author yichenshanren
 * @date 2018/7/9
 */

public class GiftInoutVo {

    private BigDecimal incoming;
    private BigDecimal outgoings;

    public BigDecimal getIncoming() {
        return incoming;
    }

    public void setIncoming(BigDecimal incoming) {
        this.incoming = incoming;
    }

    public BigDecimal getOutgoings() {
        return outgoings;
    }

    public void setOutgoings(BigDecimal outgoings) {
        this.outgoings = outgoings;
    }
}
