package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoAffair;
import com.chouchongkeji.service.user.memo.vo.HomeMemoItemVo;
import com.chouchongkeji.service.user.memo.vo.MemoItemVo;
import com.chouchongkeji.service.user.memo.vo.MemoItemVoV2;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
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
    List<HomeMemoItemVo> selectLastByUserId(@Param("userId") Integer userId,@Param("start") Long start,@Param("end") Long end);

    int insertBatch(@Param("affair") MemoAffair affair,@Param("list") List<Date> list);

    List<MemoItemVo> selectAllCByUserId(@Param("userId") Integer userId);

    /**
     * 按周循环
     * @param userId
     * @return
     */
    List<MemoItemVo> selectAllByUserIdWeek(Integer userId);

    /**
     * 按月循环
     * @param userId
     * @return
     */
    List<MemoItemVo> selectAllByUserIdMonth(Integer userId);

    /**
     * 所有循环
     * @param userId
     * @return
     */
    List<MemoItemVo> selectAllByUserId(Integer userId);

    /**
     * 关键字查询所有事件
     * @param userId
     * @return
     */
    List<MemoItemVo> selectAllByUserIdKeyword(@Param("userId") Integer userId,@Param("keyword") String keyword);

    void updateById(Integer id);



    /**
     * 普通事件详情
     * @param id 事件id
     * @return
     */
    MemoItemVoV2 selectByKey(Integer id);
}
