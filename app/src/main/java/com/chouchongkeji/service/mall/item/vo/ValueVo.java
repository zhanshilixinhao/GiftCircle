package com.chouchongkeji.service.mall.item.vo;

import java.util.Objects;

/**
 * @author linqin
 * @date 2018/6/19
 */
public class ValueVo {

    private Integer valueId;

    private String value;

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueVo valueVo = (ValueVo) o;
        return Objects.equals(valueId, valueVo.valueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueId);
    }
}
