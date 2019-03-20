package com.chouchongkeji.dial.dao.user;

import com.chouchongkeji.dial.pojo.user.LeaveMessage;

public interface LeaveMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeaveMessage record);

    int insertSelective(LeaveMessage record);

    LeaveMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LeaveMessage record);

    int updateByPrimaryKey(LeaveMessage record);
}