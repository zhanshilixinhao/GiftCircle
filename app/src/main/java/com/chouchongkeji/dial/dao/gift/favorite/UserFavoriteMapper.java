package com.chouchongkeji.dial.dao.gift.favorite;

import com.chouchongkeji.dial.pojo.gift.favorite.UserFavorite;
import com.chouchongkeji.service.gift.favorite.vo.FavoriteListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @return: com.chouchongkeji.dial.pojo.gift.favorite.UserFavorite
     * @author: yy
     * @Date: 2018/6/13
     */
    UserFavorite selectByUserIdAndItemId(@Param("userId") Integer userId, @Param("id") Integer id);

    /**
     * 取消收藏
     *
     * @param: [userId 用户id, id 商品id]
     * @return: int
     * @author: yy
     * @Date: 2018/6/13
     */
    int deleteByUserIdAndItemId(@Param("userId") Integer userId, @Param("id") Integer id);

    /**
     * 获取用户收藏列表
     *
     * @param: [userId 用户id]
     * @return: java.util.List<com.chouchongkeji.service.gift.favorite.vo.FavoriteListVo>
     * @author: yy
     * @Date: 2018/6/13
     */
    List<FavoriteListVo> selectByUserId(@Param("userId") Integer userId);
}