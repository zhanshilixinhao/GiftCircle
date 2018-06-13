package com.chouchongkeji.service.gift.item.impl;

import com.chouchongkeji.dao.gift.item.ItemCategoryMapper;
import com.chouchongkeji.dao.gift.item.ItemMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.pojo.gift.item.Item;
import com.chouchongkeji.pojo.gift.item.ItemCategory;
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.service.gift.item.ItemService;
import com.chouchongkeji.service.gift.item.vo.ItemDetail;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author linqin
 * @date 2018/6/12
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemCategoryMapper itemCategoryMapper;

    @Autowired
    private ServiceProperties serviceProperties;

    /**
     * 商品分类列表
     *
     * @return
     * @author linqin
     * @date 2018/6/12
     */
    @Override
    public Response getCategoryList() {
        List<ItemCategory> itemCategory = itemCategoryMapper.selectAll();
        return ResponseFactory.sucData(itemCategory);
    }


    /**
     * 商品列表查询
     *
     * @param classes  查询类型 1-精选，2-热门，3-新品
     * @param gender   筛选性别 0-默认，1-男，2-女
     * @param minAge   最小年龄
     * @param maxAge   最大年龄
     * @param minPrice 最低价格
     * @param maxPrice 最高价格
     * @param eventId  事件id
     * @return
     * @author linqin
     * @date 2018/6/12
     */
    @Override
    public Response getItemList(Integer classes, Integer gender, Integer minAge, Integer maxAge,
                                BigDecimal minPrice, BigDecimal maxPrice, Integer eventId, PageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        List<Item> list = itemMapper.selectAll(classes, gender, minAge, maxAge, minPrice, maxPrice, eventId);
        return ResponseFactory.sucData(list);
    }

    /**
     * 获取商品详情
     *
     * @param: [id 商品id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    @Override
    public Response getItemDetail(Integer id) {
        ItemDetail itemDetail = itemMapper.selectDetailByIteamId(id);
        if (itemDetail != null) {
            itemDetail.setDetailUrl(serviceProperties.getProductDetail() + id);
            return ResponseFactory.sucData(itemDetail);
        } else {
            return ResponseFactory.err("无此商品");
        }
    }

    /**
     * 获得商品详情html
     *
     * @param: [id 商品id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    @Override
    public Response getHtmlItemDetail(Integer id) {
        String detail = itemMapper.selectGetHtmlDetail(id);
        return ResponseFactory.sucData(detail);
    }
}
