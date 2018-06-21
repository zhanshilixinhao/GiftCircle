package com.chouchongkeji.dial.dao.friend;

import com.chouchongkeji.dial.pojo.friend.Friend;
import org.apache.ibatis.annotations.Param;

public interface FriendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);

    Friend selectByUserIdAndFriendUserId(@Param("userId") Integer userId,
                                         @Param("friendUserId") Integer friendUserId);
}