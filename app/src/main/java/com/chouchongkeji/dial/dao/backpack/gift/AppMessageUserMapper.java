package com.chouchongkeji.dial.dao.backpack.gift;

import com.chouchongkeji.dial.pojo.gift.virtualItem.AppMessageUser;
import com.chouchongkeji.service.message.vo.ConsignmentMessageVo;
import com.chouchongkeji.service.message.vo.GiftMessageVo;
import com.chouchongkeji.service.message.vo.MessageHomeVo;
import com.chouchongkeji.service.message.vo.SystemMessageVo;
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

    List<GiftMessageVo> selectListByUserIdAndType(@Param("userId") Integer userId);

    List<ConsignmentMessageVo> selectConMessageByUserId(@Param("userId") Integer userId);

    /**
     * 系统消息列表
     * @param userId
     * @return
     */
    List<SystemMessageVo> selectSystemByUserIdAndType(@Param("userId")Integer userId);


    int updateByUserIdAndType(@Param("userId")Integer userId,@Param("messageType") Byte messageType);
}
