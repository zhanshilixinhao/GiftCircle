package com.chouchongkeji.service.gift.favorite;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author yy
 * @date 2018/6/13
 **/
public interface FavoriteService {
    /**
     * 用户收藏商品或者取消收藏商品
     *
     * @param: [userId 用户id, id 商品id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    Response isCollection(Integer userId, Integer id);

    /**
     * 获得收藏列表
     *
     * @param: [userId 用户id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    Response getItemList(Integer userId);

    /**
     * 批量删除收藏商品
     *
     * @param: [userId, ids]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    Response delCollectionItem(Integer userId, String ids);
}
