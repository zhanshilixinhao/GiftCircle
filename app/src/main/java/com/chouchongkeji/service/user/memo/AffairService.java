package com.chouchongkeji.service.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoAffair;
import com.chouchongkeji.goexplore.common.Response;

import java.util.HashSet;

/**
 * @author linqin
 * @date 2019/3/11 16:20
 */

public interface AffairService {

    /**
     * 添加备忘录事件类型
     *
     * @param userId
     * @param name        类型名称
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    Response addEventType(Integer userId, String name);

    /**
     * 修改备忘录事件类型
     *
     * @param userId
     * @param name        类型名称
     * @param eventId       事件类型id
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    Response updateEventType(Integer userId, String name, Integer eventId);

    /**
     * 删除备忘录事件类型
     *
     * @param userId
     * @param eventId       事件类型id
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    Response deleteEventType(Integer userId, Integer eventId);

    /**
     * 添加事件
     *
     * @param userId
     * @param memoAffair
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    Response addAffair(Integer userId, MemoAffair memoAffair, HashSet<Integer> idSet, Byte isCirculation);

    /**
     * 备忘录事件类型列表
     *
     * @param userId
     * @author linqin
     * @date 2019/3/11
     */
    Response getEventTypeList(Integer userId);
    /**
     * 修改备忘录事件
     *
     * @param userDetails
     * @param affair
     * @return
     * @author linqin
     * @date 2019/3/12
     */
    Response modifyAffair(Integer userId, MemoAffair affair, HashSet<Integer> idSet);

    /**
     * 删除一个备忘录信息
     *
     * @param userDetails 用户信息
     * @param id          备忘录 id
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    Response delMemo(Integer userId, Integer id);

    /**
     * 获取备忘录列表
     *
     * @param userDetails
     * @param start
     * @param end
     * @return
     * @author linqin
     * @date 2019/1/7 16:38
     */
    Response getAffairList(Integer userId, Long start, Long end);
}
