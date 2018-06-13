package com.chouchongkeji.dao.gift.favorite;

import com.chouchongkeji.pojo.gift.favorite.UserFavorite;
import org.apache.ibatis.annotations.Param;

public interface UserFavoriteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFavorite record);

    int insertSelective(UserFavorite record);

    UserFavorite selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFavorite record);

    int updateByPrimaryKey(UserFavorite record);

    /**
     * 查询用户是否收藏该商品
     *
     * @param: [userId 用户id, id 商品id]
     * @return: com.chouchongkeji.pojo.gift.favorite.UserFavorite
     * @author: yy
     * @Date: 2018/6/13
     */
    UserFavorite selectByUserIdAndItemId(@Param("userId") Integer userId, @Param("id") Integer id);
}