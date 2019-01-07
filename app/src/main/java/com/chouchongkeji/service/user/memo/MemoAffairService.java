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
    Response addAffair(Integer userId, MemoAffair affair, HashSet<Integer> idSet);

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
}
