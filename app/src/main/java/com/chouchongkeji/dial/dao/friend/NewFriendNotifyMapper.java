package com.chouchongkeji.dial.dao.friend;

import com.chouchongkeji.dial.pojo.friend.NewFriendNotify;
import com.chouchongkeji.service.friend.vo.NotifyMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewFriendNotifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewFriendNotify record);

    int insertSelective(NewFriendNotify record);

    NewFriendNotify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewFriendNotify record);

    int updateByPrimaryKey(NewFriendNotify record);

    List<NotifyMsg> selectByUserId(@Param("userId") Integer userId);
}