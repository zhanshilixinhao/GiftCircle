package com.chouchongkeji.service.user.friend;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author linqin
 * @date 2019/3/20 14:42
 */
public interface LeaveMessageService {

    /**
     * 给好友留言
     *
     * @param userId
     * @param friendUserId 好友id
     * @param detail       留言内容
     * @return
     * @author linqin
     * @date 2019/3/20
     */
    Response leaveMessage(Integer userId, Integer friendUserId, String detail);
}
