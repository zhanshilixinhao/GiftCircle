package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoAffair;
import com.chouchongkeji.service.user.memo.vo.HomeMemoItemVo;
import com.chouchongkeji.service.user.memo.vo.MemoItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemoAffairMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemoAffair record);

    int insertSelective(MemoAffair record);

    MemoAffair selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemoAffair record);

    int updateByPrimaryKey(MemoAffair record);

    List<MemoItemVo> selectByUserIdAndDate(@Param("userId") Integer userId,@Param("start") Long start,@Param("end") Long end);

    List<MemoItemVo> selectFriendByUserIdAndDate(@Param("userId") Integer userId,@Param("start") Long start,@Param("end") Long end);

    /**
     * 删除活动
     * @param id
     * @param userId
     * @return
     */
//    int deleteByPrimaryKeyAndUserId(@Param("id") Integer id, @Param("userId") Integer userId);

    /**
     * 首页的三个备忘录
     * @param userId
     * @return
     */
    List<HomeMemoItemVo> selectLastByUserId(Integer userId);
}
