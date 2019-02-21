package com.chouchongkeji.dial.dao.friend;

import com.chouchongkeji.dial.pojo.friend.FriendGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FriendGroup record);

    int insertSelective(FriendGroup record);

    FriendGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FriendGroup record);

    int updateByPrimaryKey(FriendGroup record);


    FriendGroup selectByUserIdAndGroupName(@Param("userId") Integer userId,
                                           @Param("groupName") String groupName);

    int deleteByPrimaryKeyAndUserId(@Param("groupId") Integer groupId,
                                    @Param("userId") Integer userId);

    List<FriendGroup> selectByUserId(@Param("userId") Integer userId);

    /**
     * 查询用户分组数量
     * @param userId
     * @return
     */
    int selectCountByUserId(Integer userId);
}
