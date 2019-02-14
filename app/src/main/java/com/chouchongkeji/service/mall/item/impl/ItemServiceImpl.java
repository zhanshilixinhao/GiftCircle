package com.chouchongkeji.service.mall.item.impl;

import com.chouchongkeji.dial.dao.gift.article.ArticleMapper;
import com.chouchongkeji.dial.dao.gift.favorite.UserFavoriteMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemCategoryMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemCommentMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemMapper;
import com.chouchongkeji.dial.pojo.gift.article.Article;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.dial.pojo.gift.favorite.UserFavorite;
import com.chouchongkeji.dial.pojo.gift.item.Item;
import com.chouchongkeji.dial.pojo.gift.item.ItemCategory;
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.service.mall.item.ItemService;
import com.chouchongkeji.service.mall.item.vo.ItemArticleListVo;
import com.chouchongkeji.service.mall.item.vo.ItemCommentVo;
import com.chouchongkeji.service.mall.item.vo.ItemDetail;
import com.chouchongkeji.service.mall.item.vo.ItemListVo;
import com.github.pagehelper.PageHelper;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Autowired
    private ArticleMapper articleMapper;

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
                                BigDecimal minPrice, BigDecimal maxPrice, Integer eventId, PageQuery pageQuery,Integer categoryId) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        List<Item> list = itemMapper.selectAll(categoryId,classes, gender, minAge, maxAge, minPrice, maxPrice, eventId);
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



    /**
     * 商品搜索
     * @param keyword 关键字
     * @return
     * @author: linqin
     * @Date: 2018/7/6
     */
    @Override
    public Response searchItem(String keyword) {
        List<ItemListVo> list = itemMapper.selectItemList(keyword);
        return ResponseFactory.sucData(list);
    }


    /**
     * 商品文章搜索
     *
     * @param keyword 关键字
     * @return
     * @author: linqin
     * @Date: 2018/7/6
     */
    @Override
    public Response searchItemArticle(String keyword) {
        List<ItemArticleListVo> listVo = new ArrayList<>();
        ItemArticleListVo vo = new ItemArticleListVo();
        // 查询商品
        List<ItemListVo> items = itemMapper.selectItemList(keyword);
        if (!CollectionUtils.isEmpty(items)){
            for (ItemListVo item : items) {
                vo = new ItemArticleListVo();
                vo.setId(item.getItemId());
                vo.setTitle(item.getTitle());
                vo.setCover(item.getCover());
                vo.setPrice(item.getPrice());
                vo.setType(1);
                listVo.add(vo);
            }
        }
        // 根据关键字查询文章
        List<Article> articles = articleMapper.selectArticleList(keyword);
        if (!CollectionUtils.isEmpty(articles)){
            for (Article article : articles) {
                vo = new ItemArticleListVo();
                vo.setId(article.getId());
                vo.setTitle(article.getTitle());
                vo.setCover(article.getCover());
                vo.setSummary(article.getSummary());
                vo.setType(2);
                listVo.add(vo);
            }
        }
        return ResponseFactory.sucData(listVo);
    }
}
