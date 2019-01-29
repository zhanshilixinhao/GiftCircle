package com.chouchongkeji.dial.dao.friend;

import com.chouchongkeji.dial.pojo.friend.NewFriendNotify;
import com.chouchongkeji.service.user.friend.vo.FriendMessageVo;
import com.chouchongkeji.service.user.friend.vo.NotifyMsg;
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

    /**
     * 新的好友未查看数量
     * @param userId
     * @param time
     * @return
     */
    FriendMessageVo selectByUserIdTime(@Param("userId") Integer userId,@Param("time") long time);
}
