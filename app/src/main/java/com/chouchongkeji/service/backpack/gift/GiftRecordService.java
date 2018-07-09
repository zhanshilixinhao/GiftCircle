package com.chouchongkeji.service.backpack.gift;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author yichenshanren
 * @date 2018/7/9
 */

public interface GiftRecordService {

    /**
     * 获取一个用户的总的收支
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/7/9
     */
    Response getInEx(Integer userId);
}
