package com.chouchongkeji.mvc.controller.backpack.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.item.DiscountingService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linqin
 * @date 2018/7/11
 */
@RestController
@RequestMapping("auth/v1/discount")
public class DiscountingController {

    @Autowired
    private DiscountingService discountingService;


    /**
     * 背包物品折现
     *
     * @param userDetails
     * @param bpId        背包id
     * @return
     * @author linqin
     * @date 2018/7/11
     */
    @PostMapping("add_record")
    public Response addDiscountRecord(@AuthenticationPrincipal UserDetails userDetails, Long bpId) {
        //校验参数
        if (bpId == null) {
            return ResponseFactory.errMissingParameter();
        }
        return discountingService.addDiscountRecord(userDetails.getUserId(), bpId);
    }

    /**
     * 折现信息比例
     *
     * @param userDetails
     * @return
     * @author linqin
     * @date 2018/7/11
     */
    @PostMapping("preinfo")
    public Response preinfo(@AuthenticationPrincipal UserDetails userDetails) {
        List<HashMap> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("ratio", 0.20);
        map.put("desc", "平台收取商品原价的20%作为仓储及物流服务费");
        map.put("type",1);
        list.add((HashMap) map);
        map = new HashMap<>();
        map.put("ratio", 0);
        map.put("desc", "不扣除20%手续费");
        map.put("type",2);
        list.add((HashMap) map);
        return ResponseFactory.sucData(list);
    }


//    /**
//     * 背包物品折现记录
//     * @param userDetails
//     * @param status 状态,0-所有折现记录，1-已完成的折现记录，2-未完成的折现记录
//     * @return
//     * @author linqin
//     * @date 2018/7/11
//     */
//    @PostMapping("add_record")
//    public Response discountRecord(@AuthenticationPrincipal UserDetails userDetails,Integer status){
//        //校验参数
//        if (status == null){
//
//        }
//        return discountingService.discountRecord(userDetails.getUserId(),status);
//    }


}
