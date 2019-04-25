package com.chouchongkeji.dial.dao.user;

import com.chouchongkeji.dial.pojo.user.UserTagInfo;
import org.apache.ibatis.annotations.Param;

public interface UserTagInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTagInfo record);

    int insertSelective(UserTagInfo record);

    UserTagInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTagInfo record);

    int updateByPrimaryKey(UserTagInfo record);

    /**
     * 根据用户id和好友id查询
     * @param userId
     * @param friendUserId
     * @return
     */
    UserTagInfo selectByUserIdFriendUserId(@Param("userId") Integer userId,@Param("friendUserId") Integer friendUserId);
}
