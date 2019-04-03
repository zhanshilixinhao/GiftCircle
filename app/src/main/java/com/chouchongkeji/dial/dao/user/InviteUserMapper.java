package com.chouchongkeji.dial.dao.user;

import com.chouchongkeji.dial.pojo.user.InviteUser;

public interface InviteUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InviteUser record);

    int insertSelective(InviteUser record);

    InviteUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InviteUser record);

    int updateByPrimaryKey(InviteUser record);
}