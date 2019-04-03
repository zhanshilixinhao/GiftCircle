package com.chouchongkeji.dial.dao.user;

import com.chouchongkeji.dial.pojo.user.InviteUser;
import com.chouchongkeji.service.iwant.wallet.vo.InviteUserVo;

import java.util.List;

public interface InviteUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InviteUser record);

    int insertSelective(InviteUser record);

    InviteUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InviteUser record);

    int updateByPrimaryKey(InviteUser record);

    /**
     * 查询被邀请的所有用户
     * @param userId
     * @return
     */
    List<InviteUserVo> selectAllList(Integer userId);
}