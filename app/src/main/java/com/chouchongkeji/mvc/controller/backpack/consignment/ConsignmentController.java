package com.chouchongkeji.mvc.controller.backpack.consignment;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.consignment.ConsignmentService;
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
     * @param bpId    背包id
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
        return consignmentService.getInfo(userDetails.getUserId(),bpId);
    }


    /**
     * 上架寄售台
     * @param userDetails
     * @param bpId 背包Id
     * @param price 商品上架价格
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    @PostMapping("putaway")
    public Response putawayItem(@AuthenticationPrincipal UserDetails userDetails, Long bpId, BigDecimal price){
        //校验参数
        if (bpId == null) {
            return ResponseFactory.errMissingParameter();
        }
        if (price==null){
            return ResponseFactory.err("价格不能为空");
        }
        return consignmentService.putawayItem(userDetails.getUserId(),bpId,price);
    }


}
