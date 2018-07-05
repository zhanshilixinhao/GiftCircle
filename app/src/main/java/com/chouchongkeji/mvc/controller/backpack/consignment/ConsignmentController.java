package com.chouchongkeji.mvc.controller.backpack.consignment;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.consignment.ConsignmentService;
import com.chouchongkeji.util.Constants;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2018/7/2
 */
@RestController
@RequestMapping("auth/consignment")
public class ConsignmentController {

    @Autowired
    private ConsignmentService consignmentService;

    /**
     * 上架寄售台之前商品信息查询
     *
     * @param userDetails
     * @param bpId        背包id
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    @PostMapping("info")
    public Response getInfo(@AuthenticationPrincipal UserDetails userDetails, Long bpId) {
        //校验参数
        if (bpId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return consignmentService.getInfo(userDetails.getUserId(), bpId);
    }


    /**
     * 上架寄售台
     *
     * @param userDetails
     * @param bpId        背包Id
     * @param price       商品上架价格
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    @PostMapping("putaway")
    public Response putawayItem(@AuthenticationPrincipal UserDetails userDetails, Long bpId, BigDecimal price) {
        //校验参数
        if (bpId == null) {
            return ResponseFactory.errMissingParameter();
        }
        if (price == null) {
            return ResponseFactory.err("价格不能为空");
        }
        return consignmentService.putawayItem(userDetails.getUserId(), bpId, price);
    }


    /**
     * 寄售台卖家/买家列表
     *
     * @param userDetails
     * @param user        卖家/买家  1-卖家，2-买家
     * @param condition       商家订单状态 1-全部 ，2-交易中，3-已完成
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    @PostMapping("bs_list")
    public Response buySellList(@AuthenticationPrincipal UserDetails userDetails, Byte user, Byte condition, PageQuery pageQuery) {
        //校验参数
        if (user == null || (user != Constants.SELLER_BUYER.SELLER && user != Constants.SELLER_BUYER.BUYER)) {
            return ResponseFactory.errMissingParameter();
        }
        if (condition == null || (condition != Constants.BUSINESS_ORDER_STATUS.ALL && condition != Constants.BUSINESS_ORDER_STATUS.TRADING
                        && condition!=Constants.BUSINESS_ORDER_STATUS.DONE)) {
            return ResponseFactory.errMissingParameter();
        }
        return consignmentService.buySellList(userDetails.getUserId(),user,condition,pageQuery);
    }


}
