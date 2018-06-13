package com.chouchongkeji.controller.gift.virtualItem;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.gift.virtualItem.VirtualItemService;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yy
 * @date 2018/6/11
 **/

@RestController
@RequestMapping("noauth/v1/virItem")
public class VirtualItemController {
    @Autowired
    private VirtualItemService virtualItemService;

    /**
     * 获得虚拟商品分类列表
     *
     * @param: [userDetails 用户认证信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    @PostMapping("cateList")
    public Response getVirItemCategoryList(){
        return virtualItemService.getVirItemCategoryList();
    }

    /**
     * 获得虚拟商品列表
     *
     * @param: [userDetails 用户认证信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    @PostMapping("list")
    public Response getVirItemList(PageQuery page, Integer id){
        return virtualItemService.getVirItemList(id, page);
    }
}
