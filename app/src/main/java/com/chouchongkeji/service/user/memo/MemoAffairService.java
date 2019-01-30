package com.chouchongkeji.service.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoAffair;
import com.chouchongkeji.goexplore.common.Response;

import java.util.HashSet;

/**
 * @author linqin
 * @date 2019/1/7 20:41
 */

public interface MemoAffairService {


    /**
     * 添加备忘录事件
     *
     * @param userId
     * @param affair
     * @return
     * @author linqin
     * @date 2019/1/7 16:38
     */
    Response addAffair(Integer userId, MemoAffair affair, HashSet<Integer> idSet,Byte isCirculation);

    /**
     * 修改备忘录事件
     *
     * @param userId
     * @param affair
     * @return
     * @author linqin
     * @date 2019/1/7 16:38
     */
    Response modifyAffair(Integer userId, MemoAffair affair, HashSet<Integer> idSet);

    /**
     * 获取活动列表
     *
     * @param userId
     * @param start
     * @param end
     * @return
     * @author linqin
     * @date 2019/1/7 16:38
     */
    Response getAffairList(Integer userId, Long start, Long end);

    /**
     * 获得好友的备忘录
     *
     * @param userId 用户信息
     * @param start       开始时间
     * @param end         结束时间
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    Response getListForFriend(Integer userId, Long start, Long end, Integer friendUserId);


    /**
     * 删除一个备忘录信息
     *
     * @param userId 用户信息
     * @param id          备忘录 id
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    Response delMemo(Integer userId, Integer id);
    /**
     * 首页的三个备忘录
     *
     * @param userId 用户信息
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    Response getHomeList(Integer userId);
}
