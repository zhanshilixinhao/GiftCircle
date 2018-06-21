package com.chouchongkeji.service.gift.item.vo;

import java.util.Objects;

/**
 * @author linqin
 * @date 2018/6/20
 */
public class OrderVo {

    private Integer skuId;

    private Integer quantity;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
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
        OrderVo orderVo = (OrderVo) o;
        return Objects.equals(skuId, orderVo.skuId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(skuId);
    }
}
