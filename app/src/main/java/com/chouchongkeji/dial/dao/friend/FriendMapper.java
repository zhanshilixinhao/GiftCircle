package com.chouchongkeji.dial.dao.friend;

import com.chouchongkeji.dial.pojo.friend.Friend;
import com.chouchongkeji.service.user.friend.vo.FriendItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);

    Friend selectByUserIdAndFriendUserId(@Param("userId") Integer userId,
                                         @Param("friendUserId") Integer friendUserId);

    /**
     * 将用户从一个分组移到另一个扽组
     *
     *
     * @param userId
     * @param groupId
     * @param newGroupId
     * @return
     */
    int updateMoveToGroup(@Param("userId") Integer userId, @Param("groupId") Integer groupId,
                          @Param("newGroupId") Integer newGroupId);

    List<FriendItem> selectByUserIdAndGroupId(@Param("userId") Integer userId,
                                              @Param("groupId") Integer groupId);

    int deleteByUserIdAndFriendUserId(@Param("userId") Integer userId,
                                      @Param("friendUserId") Integer friendUserId);
}