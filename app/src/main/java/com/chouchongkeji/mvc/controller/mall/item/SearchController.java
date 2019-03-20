package com.chouchongkeji.mvc.controller.mall.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.mall.item.SearchService;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2019/3/20 10:16
 */
@RestController
@RequestMapping("auth/search")
public class SearchController {

    @Autowired
    private SearchService searchService;


    /**
     * 商品文章事件搜索
     *
     * @param keyword 关键字
     * @return
     * @author: linqin
     * @Date: 2018/7/6
     */
    @PostMapping("all_list")
    public Response searchItemArticle(@AuthenticationPrincipal UserDetails userDetails, String keyword,
                                      @RequestParam(name = "type",defaultValue = "1") Integer type) {
        //校验参数
        if (StringUtils.isBlank(keyword)) {
            return ResponseFactory.errMissingParameter();
        }
        return searchService.searchItemArticle(userDetails.getUserId(),keyword,type);
    }

}
