package com.chouchongkeji.mvc.controller.mall.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.mall.item.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @author linqin
 * @date 2019/6/24
 */
@RestController
@RequestMapping("noauth/v2/recommend")
public class RecommendController {


    @Autowired
    private RecommendService recommendService;


    /**
     * 今日推荐
     *
     * @return
     * @author linqin
     * @date 2019/6/24
     */
    @PostMapping("list")
    public Response getRecommendItem() throws ParseException {
        return recommendService.getRecommendItem();
    }


}
