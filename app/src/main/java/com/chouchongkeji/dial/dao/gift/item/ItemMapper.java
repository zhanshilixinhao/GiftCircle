package com.chouchongkeji.dial.dao.gift.item;

import com.chouchongkeji.dial.pojo.gift.item.Item;
import com.chouchongkeji.service.backpack.consignment.vo.DetailVo;
import com.chouchongkeji.service.gift.item.vo.ItemDetail;
import com.chouchongkeji.service.gift.item.vo.ItemListVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    List<Item> selectAll(@Param("categoryId") Integer categoryId, @Param("classes") Integer classes,
                         @Param("gender") Integer gender,
                         @Param("minAge") Integer minAge,
                         @Param("maxAge") Integer maxAge,
                         @Param("minPrice") BigDecimal minPrice,
                         @Param("maxPrice") BigDecimal maxPrice,
                         @Param("eventId") Integer eventId);

    /**
     * 获得商品的详情
     *
     * @param: [id 商品id]
     * @return: com.chouchongkeji.service.gift.item.vo.ItemDetail
     * @author: yy
     * @Date: 2018/6/13
     */
    ItemDetail selectDetailByIteamId(@Param("id") Integer id);

    /**
     * 获得商品的html详情
     *
     * @param: [id 商品id]
     * @return: java.lang.String
     * @author: yy
     * @Date: 2018/6/13
     */
    String selectGetHtmlDetail(@Param("id") Integer id);

    Item selectByItemId(Integer itemId);

    Integer updateSalesByItemId(@Param("itemId") Integer itemId,@Param("quantity") Integer quantity);

    DetailVo selectItemDtail(Integer skuId);

    List<ItemListVo> selectItemList(String keyword);
}