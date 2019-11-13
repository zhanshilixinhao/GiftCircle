package com.chouchongkeji.mvc.controller.v3;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.v3.CardDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2019/11/13
 */
@RequestMapping("noauth/v3/cardDetail")
@RestController
public class CardDetailController {

    @Autowired
    private CardDetailService cardDetailService;

    /**
     * 获取会员卡使用说明
     * @param id 会员卡id
     * @return
     */
    @PostMapping("html")
    public Response getCardDetail(Integer id){
        if (id == null){
            return ResponseFactory.errMissingParameter();
        }
        return cardDetailService.getCardDetail(id);
    }

}
