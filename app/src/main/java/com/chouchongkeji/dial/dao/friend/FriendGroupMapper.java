package com.chouchongkeji.dial.dao.friend;

import com.chouchongkeji.dial.pojo.friend.FriendGroup;

public interface FriendGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FriendGroup record);

    int insertSelective(FriendGroup record);

    FriendGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FriendGroup record);

    int updateByPrimaryKey(FriendGroup record);
}