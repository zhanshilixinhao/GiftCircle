package com.chouchongkeji.service.mall.item.impl;

import com.chouchongkeji.dial.dao.gift.item.ItemFeatureMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemSkuMapper;
import com.chouchongkeji.dial.pojo.gift.item.ItemSku;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.dial.pojo.gift.item.Item;
import com.chouchongkeji.service.mall.item.SkuService;
import com.chouchongkeji.service.mall.item.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.x509.RFC822Name;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author linqin
 * @date 2018/6/15
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemSkuMapper itemSkuMapper;

    @Autowired
    private ItemFeatureMapper itemFeatureMapper;

    /**
     * 获取商品的sku组合
     *
     * @param itemId 商品id
     * @return
     * @author linqin
     * @date 2018/6/15
     */
    @Override
    public Response getSkuSet(Integer itemId) {
        //根据商品Id查出该商品
        Item itemInfo = itemMapper.selectByItemId(itemId);
        //校验该商品是否存在
        if (itemInfo == null) {
            return ResponseFactory.errMissingParameter();
        }
        //商品存在，查询该商品的所有属性
//        List<ItemFeatureVo> itemFeatures = itemFeatureMapper.selectFeatureByItemId(itemId);
        List<SkuListVo> list;
        //如果存在属性
//        if (CollectionUtils.isNotEmpty(itemFeatures)){
        //查询该商品的sku
        list = itemSkuMapper.selectByItemId(itemId);
//        }

        List<ItemFeatureVo> itemFeatures = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(list)) {
            for (SkuListVo vo : list) {
                for (SkuValueVo value : vo.getValues()) {

                    ItemFeatureVo featureVo = null;
                    for (ItemFeatureVo feature : itemFeatures) {
                        if (feature.getFeatureId().equals(value.getFeatureId())) {
                            featureVo = feature;
                            ValueVo valueVo = new ValueVo();
                            valueVo.setValue(value.getValue());
                            valueVo.setValueId(value.getValueId());
                            featureVo.getValues().add(valueVo);
                        }
                    }
                    if (featureVo == null) {
                        featureVo = new ItemFeatureVo();
                        featureVo.setFeatureId(value.getFeatureId());
                        featureVo.setName(value.getFeatureName());
                        itemFeatures.add(featureVo);
                        featureVo.setValues(new HashSet<>());
                        ValueVo valueVo = new ValueVo();
                        valueVo.setValue(value.getValue());
                        valueVo.setValueId(value.getValueId());
                        featureVo.getValues().add(valueVo);
                    }
                }
            }
        }
        FeatureValueVo vo = new FeatureValueVo();
        vo.setFeatures(itemFeatures);
        vo.setSkus(list);
        return ResponseFactory.sucData(vo);

    }



    /**
     * 根据sku获取库存
     *
     * @param skuId
     * @return
     * @author linqin
     * @date 2018/6/15
     */
    @Override
    public Response getSkuStock(Integer skuId) {
        ItemSku itemSku = itemSkuMapper.selectBySkuId(skuId);
        if (itemSku == null){
            return ResponseFactory.err("该sku不存在");
        }
        return ResponseFactory.sucData(itemSku);
    }
}
