package com.chouchongkeji.service.home;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author linqin
 * @date 2019/2/20 16:25
 */

public interface WelfareService {


    /**
     * 确认领取福利
     *
     * @param userDetails
     * @return
     * @author linqin
     * @date 2019/2/20
     */
    Response confirmWelfare(Integer userId);
}
