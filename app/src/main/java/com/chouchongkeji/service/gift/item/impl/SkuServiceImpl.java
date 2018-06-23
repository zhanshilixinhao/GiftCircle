package com.chouchongkeji.service.gift.item.impl;

import com.chouchongkeji.dial.dao.gift.item.ItemFeatureMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemSkuMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.dial.pojo.gift.item.Item;
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.service.gift.item.SkuService;
import com.chouchongkeji.service.gift.item.vo.FeatureValueVo;
import com.chouchongkeji.service.gift.item.vo.ItemFeatureVo;
import com.chouchongkeji.service.gift.item.vo.SkuListVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if (itemInfo == null){
            return ResponseFactory.errMissingParameter();
        }
        //商品存在，查询该商品的所有属性
        List<ItemFeatureVo> itemFeatures = itemFeatureMapper.selectFeatureByItemId(itemId);
        List<SkuListVo> list = new ArrayList<>();
        //如果存在属性
        if (CollectionUtils.isNotEmpty(itemFeatures)){
            //查询该商品的sku
             list = itemSkuMapper.selectByItemId(itemId);
        }
        FeatureValueVo vo = new FeatureValueVo();
        vo.setFeatures(itemFeatures);
        vo.setSkus(list);
        return ResponseFactory.sucData(vo);

    }
}
