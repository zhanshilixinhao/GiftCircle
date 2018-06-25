package com.chouchongkeji.mvc.controller.gift.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.gift.item.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yy
 * @date 2018/6/20
 **/

@RestController
@RequestMapping("noauth/v1/theme")
public class ThemeItemController {
    @Autowired
    private ThemeService themeService;

    /**
     * 获得主题列表
     *
     * @param: []
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    @PostMapping("list")
    public Response getThemeList() {
        return themeService.getThemeList();
    }

    /**
     * 获取主题商品列表
     *
     * @param: [id 主题id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    @PostMapping("item")
    public Response getThemeItemList(Integer id, PageQuery page) {
        if (id == null) {
            return ResponseFactory.errMissingParameter();
        }
        return themeService.getThemeItemList(id, page);
    }
}
