package com.chouchongkeji.mvc.controller.backpack.consignment;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.consignment.ConListService;
import com.chouchongkeji.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2018/7/2
 */
@RestController
@RequestMapping("noauth/consignment")
public class ConListController {

    @Autowired
    private ConListService conListService;

    /**
     * 查询寄售台某个商品列表
     *
     * @param targetId 目标物品id
     * @param type     1 商品 2虚拟物品 3 优惠券
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    @PostMapping("one_list")
    public Response getOneList(Integer targetId, Integer type, PageQuery pageQuery) {
        //校验参数
        if (targetId == null) {
            return ResponseFactory.errMissingParameter();
        }
        if (type == null || (type != Constants.BACKPACK_TYPE.ITEM && type != Constants.BACKPACK_TYPE.DISCOUNT_COUPON)) {
            return ResponseFactory.errMissingParameter();
        }
        return conListService.getOneList(targetId, type, pageQuery);
    }

    /**
     * 寄售台商品列表
     *
     * @param pageQuery 分页
     * @return
     * @author linqin
     * @date 2018/7/3
     */
    @PostMapping("all_list")
    public Response getAllList( PageQuery pageQuery) {
        return conListService.getAllList( pageQuery);
    }


    /**
     * 寄售台商品详情
     * @param consignmentId 寄售台商品Id
     * @return
     * @author linqin
     * @date 2018/7/3
     */
    @PostMapping("item_detail")
    public Response itemDetail(Integer consignmentId){
        //校验参数
        if (consignmentId == null){
            return ResponseFactory.errMissingParameter();
        }
        return conListService.getItemDetail(consignmentId);
    }






}
