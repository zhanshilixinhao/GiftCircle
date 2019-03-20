package com.chouchongkeji.dial.dao.friend;

import com.chouchongkeji.dial.pojo.friend.Friend;
import com.chouchongkeji.service.user.friend.vo.FriendBase;
import com.chouchongkeji.service.user.friend.vo.FriendVo;
import com.chouchongkeji.service.user.memo.vo.FriendHumVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);

    FriendVo selectByUserIdAndFriendUserId(@Param("userId") Integer userId,
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

    List<FriendVo> selectByUserIdAndGroupId(@Param("userId") Integer userId,
                                            @Param("groupId") Integer groupId);

    int deleteByUserIdAndFriendUserId(@Param("userId") Integer userId,
                                      @Param("friendUserId") Integer friendUserId);

    List<FriendBase> selectBySearch(@Param("phone") String phone,
                                    @Param("userId") Integer userId,
                                    @Param("type") Integer type);

    void updateHeartNum(@Param("userId") Integer userId,@Param("friendUserId") Integer friendUserId,@Param("num") int num);

    /**
     * 自动减少互动值
     * @param userId
     * @param friendUserId
     * @param i
     */
    void updateHeartNumByUserId(@Param("userId") Integer userId,@Param("friendUserId") Integer friendUserId);

    /**
     * 根据互动值排序
     * @param userId
     * @return
     */
    List<FriendHumVo> selectByUserId(Integer userId);

//    List<Friend> selectByUserIdAndFriendUserIdAll(@Param("userId") Integer userId,@Param("friendUserId")Integer friendUserId );
}
