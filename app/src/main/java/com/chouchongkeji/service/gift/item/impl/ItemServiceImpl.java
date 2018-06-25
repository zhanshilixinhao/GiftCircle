package com.chouchongkeji.service.gift.item.impl;

import com.chouchongkeji.dial.dao.gift.favorite.UserFavoriteMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemCategoryMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemCommentMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemMapper;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.dial.pojo.gift.favorite.UserFavorite;
import com.chouchongkeji.dial.pojo.gift.item.Item;
import com.chouchongkeji.dial.pojo.gift.item.ItemCategory;
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.service.gift.item.ItemService;
import com.chouchongkeji.service.gift.item.vo.ItemCommentVo;
import com.chouchongkeji.service.gift.item.vo.ItemDetail;
import com.chouchongkeji.service.gift.item.vo.ItemListVo;
import com.github.pagehelper.PageHelper;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Autowired
    private UserFavoriteMapper userFavoriteMapper;

    @Autowired
    private ItemCommentMapper itemCommentMapper;

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
     * @param classes  查询类型 0-默认，1-精选，2-热门
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
        List<ItemListVo> vos = new ArrayList<>();
        ItemListVo vo;
        for (Item item : list) {
            vo = new ItemListVo();
            vo.setItemId(item.getId());
            vo.setCover(item.getCover());
            vo.setTitle(item.getTitle());
            vo.setPrice(item.getPrice());
            vos.add(vo);
        }
        return ResponseFactory.sucData(vos);
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
    public Response getItemDetail(UserDetails userDetails, Integer id) {
        ItemDetail itemDetail = itemMapper.selectDetailByIteamId(id);
        if (itemDetail != null) {
            if (userDetails == null) {
                itemDetail.setIsCollect(2);
            } else {
                UserFavorite userFavorite = userFavoriteMapper.selectByUserIdAndItemId(userDetails.getUserId(), id);
                if (userFavorite == null) {
                    itemDetail.setIsCollect(2);
                } else {
                    itemDetail.setIsCollect(1);
                }
            }
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

    /**
     * 获取评论列表
     *
     * @param: [id  商品id, page 分页信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    @Override
    public Response getItemCommentList(Integer id, PageQuery page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<ItemCommentVo> itemCommentVos = itemCommentMapper.selectByItemId(id);
        return ResponseFactory.sucData(itemCommentVos);
    }
}
