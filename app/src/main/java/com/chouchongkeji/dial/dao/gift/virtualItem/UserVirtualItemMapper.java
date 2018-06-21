package com.chouchongkeji.dial.dao.gift.virtualItem;

import com.chouchongkeji.dial.pojo.gift.virtualItem.UserVirtualItem;

public interface UserVirtualItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserVirtualItem record);

    int insertSelective(UserVirtualItem record);

    UserVirtualItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserVirtualItem record);

    int updateByPrimaryKey(UserVirtualItem record);
}