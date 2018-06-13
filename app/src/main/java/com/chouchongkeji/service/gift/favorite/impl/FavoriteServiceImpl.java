package com.chouchongkeji.service.gift.favorite.impl;

import com.chouchongkeji.dao.gift.favorite.UserFavoriteMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.pojo.gift.favorite.UserFavorite;
import com.chouchongkeji.service.gift.favorite.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author yy
 * @date 2018/6/13
 **/

@Service
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


}
