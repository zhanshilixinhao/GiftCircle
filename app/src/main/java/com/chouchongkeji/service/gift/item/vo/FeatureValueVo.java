package com.chouchongkeji.service.gift.item.vo;

import java.util.List;

/**
 * @author linqin
 * @date 2018/6/19
 */
public class FeatureValueVo {

    private List<ItemFeatureVo> features;

    private List<SkuListVo> skus;

    public List<ItemFeatureVo> getFeatures() {
        return features;
    }

    public void setFeatures(List<ItemFeatureVo> features) {
        this.features = features;
    }

    public List<SkuListVo> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuListVo> skus) {
        this.skus = skus;
    }
}
