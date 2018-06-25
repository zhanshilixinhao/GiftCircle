package com.chouchongkeji.mvc.controller.gift.item;

import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.gift.item.ItemService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2018/6/12
 */
@RestController
@RequestMapping("noauth/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 商品分类列表
     *
     * @return
     * @author linqin
     * @date 2018/6/12
     */
    @PostMapping("/category_list")
    public Response ItemCategoryList() {
        return itemService.getCategoryList();
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
    @PostMapping("item_list")
    public Response itemList(Integer classes, Integer gender, Integer minAge, Integer maxAge,
                             BigDecimal minPrice, BigDecimal maxPrice, Integer eventId, PageQuery pageQuery) {
            if (classes!= null){
                if (classes>2||classes<0){
                    return ResponseFactory.err("classes错误");
                }
            }
        return itemService.getItemList(classes, gender, minAge, maxAge, minPrice, maxPrice, eventId, pageQuery);

    }

    /**
     * 获取商品详情
     *
     * @param: [id 商品id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    @PostMapping("item_detail")
    public Response getItemDetail(@AuthenticationPrincipal UserDetails userDetails, Integer id) {
        if (id == null) {
            return ResponseFactory.errMissingParameter();
        }
        return itemService.getItemDetail(userDetails, id);
    }

    /**
     * 获得商品html详情
     *
     * @param: [id 商品id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    @GetMapping("html_detail")
    public Response getHtmlItemDetail(Integer id) {
        if (id == null) {
            return ResponseFactory.errMissingParameter();
        }
        return itemService.getHtmlItemDetail(id);
    }

    /**
     * 获得评论列表
     *
     * @param: [id 商品id, page 分页信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    @PostMapping("comment_list")
    public Response getItemCommentList(Integer id, PageQuery page) {
        if (id == null) {
            return ResponseFactory.errMissingParameter();
        }
        return itemService.getItemCommentList(id, page);
    }






}
