package com.chouchongkeji.service.user.info;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author linqin
 * @date 2018/6/11
 */
public interface DistrictService {

    /**
     * 获取行政区信息
     *
     * @param level  行政区级别 province city district
     * @param pAdcode 父级行政区id
     * @return
     * @author linqin
     * @date 2018/6/11
     */
    Response getList(String level, Integer pAdcode);
}
