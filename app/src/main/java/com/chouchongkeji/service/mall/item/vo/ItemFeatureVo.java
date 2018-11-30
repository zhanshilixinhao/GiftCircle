package com.chouchongkeji.service.mall.item.vo;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author linqin
 * @date 2018/6/19
 */
public class ItemFeatureVo {

    private Integer featureId;

    private String name;

    private HashSet<ValueVo> values;

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<ValueVo> getValues() {
        return values;
    }

    public void setValues(HashSet<ValueVo> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemFeatureVo that = (ItemFeatureVo) o;
        return Objects.equals(featureId, that.featureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(featureId);
    }
}
