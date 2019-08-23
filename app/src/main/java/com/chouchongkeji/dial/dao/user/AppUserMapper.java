package com.chouchongkeji.dial.dao.user;

import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.service.user.friend.vo.UserFriendVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashSet;
import java.util.List;

public interface AppUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppUser record);

    int insertSelective(AppUser record);

    AppUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppUser record);

    int updateByPrimaryKey(AppUser record);

    AppUser selectByPhone(String phone);

    AppUser selectByUserId(Integer userId);


    List<UserFriendVo> selectByUserIdAndPhone(@Param("userId") Integer userId,@Param("list") HashSet<String> list);

    UserFriendVo selectByPhoneUserId(@Param("userId") Integer userId, @Param("phone1") String phone1);
}
