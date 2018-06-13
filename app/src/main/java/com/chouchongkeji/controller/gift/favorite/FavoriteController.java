package com.chouchongkeji.controller.gift.favorite;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.gift.favorite.FavoriteService;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yy
 * @date 2018/6/13
 **/

@RestController
@RequestMapping("auth/v1/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    /**
     * 用户收藏商品或者取消收藏商品
     *
     * @param: [userDetails 用户认证信息, id 商品id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    @PostMapping("isCollection")
    public Response isCollection(@AuthenticationPrincipal UserDetails userDetails, Integer id) {
        if (id == null) {
            return ResponseFactory.errMissingParameter();
        }
        return favoriteService.isCollection(userDetails.getUserId(), id);
    }

    /**
     * 获得用户收藏的商品的信息
     *
     * @param: [userDetails]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    @PostMapping("itemList")
    public Response getItemList(@AuthenticationPrincipal UserDetails userDetails) {
        return favoriteService.getItemList(userDetails.getUserId());
    }

    /**
     * 批量删除收藏商品
     *
     * @param: [userDetails, ids 收藏商品id集合]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    @PostMapping("delItem")
    public Response delCollectionItem(@AuthenticationPrincipal UserDetails userDetails, String ids) {
        if (StringUtils.isAnyBlank(ids)) {
            return ResponseFactory.errMissingParameter();
        }
        return favoriteService.delCollectionItem(userDetails.getUserId(), ids);
    }
}
