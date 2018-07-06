package com.chouchongkeji.dial.dao.backpack.gift;

import com.chouchongkeji.dial.pojo.gift.virtualItem.AppMessageUser;
import com.chouchongkeji.service.message.vo.MessageHomeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppMessageUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppMessageUser record);

    int insertSelective(AppMessageUser record);

    AppMessageUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppMessageUser record);

    int updateByPrimaryKey(AppMessageUser record);

    int insertBatch(List<AppMessageUser> list);

    Integer selectUnredByUserIdAndType(@Param("userId") Integer userId,
                                       @Param("type") byte type);

    MessageHomeVo selectLastMessageByUserIdAndType(@Param("userId") Integer userId,
                                                   @Param("type") byte type);
}