package com.chouchongkeji.service.gift.favorite.impl;

import com.chouchongkeji.dial.dao.gift.favorite.UserFavoriteMapper;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.dial.pojo.gift.favorite.UserFavorite;
import com.chouchongkeji.service.gift.favorite.FavoriteService;
import com.chouchongkeji.service.gift.favorite.vo.FavoriteListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author yy
 * @date 2018/6/13
 **/

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private UserFavoriteMapper userFavoriteMapper;

    @Override
    public Response isCollection(Integer userId, Integer id) {
        UserFavorite userFavorite = userFavoriteMapper.selectByUserIdAndItemId(userId, id);
        if (userFavorite == null) {
            userFavorite = new UserFavorite();
            userFavorite.setUserId(userId);
            userFavorite.setUpdated(new Date());
            userFavorite.setType((byte) 1);
            userFavorite.setTargetId(id);
            userFavorite.setCreated(new Date());
            int count = userFavoriteMapper.insert(userFavorite);
            if (count == 1) {
                return ResponseFactory.sucMsg("收藏成功");
            } else {
                return ResponseFactory.err("收藏失败");
            }
        }
        int count = userFavoriteMapper.deleteByUserIdAndItemId(userId, id);
        if (count == 1) {
            return ResponseFactory.sucMsg("取消收藏成功");
        }
        return ResponseFactory.err("取消收藏失败");
    }

    /**
     * 获得收藏列表
     *
     * @param: [userId 用户id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    @Override
    public Response getItemList(Integer userId) {
        List<FavoriteListVo> favoriteListVos = userFavoriteMapper.selectByUserId(userId);
        return ResponseFactory.sucData(favoriteListVos);
    }

    /**
     * 批量删除收藏商品
     *
     * @param: [userId, ids]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/13
     */
    @Override
    public Response delCollectionItem(Integer userId, String ids) {
        String [] array = ids.split(",");
        for (int i = 0; i < array.length; i++) {
            int id = Integer.parseInt(array[i]);
            userFavoriteMapper.deleteByPrimaryKey(id);
        }
        return ResponseFactory.sucMsg("删除成功");
    }


}
