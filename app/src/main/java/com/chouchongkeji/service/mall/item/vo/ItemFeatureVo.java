package com.chouchongkeji.service.mall.item.vo;

import java.util.List;

/**
 * @author linqin
 * @date 2018/6/19
 */
public class ItemFeatureVo {

    private Integer featureId;

    private String name;

    private List<ValueVo> values;

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

    public List<ValueVo> getValues() {
        return values;
    }

    public void setValues(List<ValueVo> values) {
        this.values = values;
    }
}
