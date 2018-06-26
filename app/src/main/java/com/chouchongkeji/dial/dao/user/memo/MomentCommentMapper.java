package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MomentComment;

public interface MomentCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MomentComment record);

    int insertSelective(MomentComment record);

    MomentComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MomentComment record);

    int updateByPrimaryKey(MomentComment record);
}