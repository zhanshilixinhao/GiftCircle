package com.chouchongkeji.service.backpack.gift.vo;

import java.util.Objects;

/**
 * @author linqin
 * @date 2018/6/20
 */
public class SendWXVo {

    private Long bpId;

    private Integer quantity;

    public Long getBpId() {
        return bpId;
    }

    public void setBpId(Long bpId) {
        this.bpId = bpId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendWXVo sendWXVo = (SendWXVo) o;
        return Objects.equals(bpId, sendWXVo.bpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bpId);
    }


}
