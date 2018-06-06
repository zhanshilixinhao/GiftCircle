package com.chouchongkeji.controller.user;

import com.chouchongkeji.goexplore.common.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yichenshanren
 * @date 2018/6/6
 */

@RestController
@RequestMapping("auth/district")
public class DistrictController {

    /**
     * 获取行政区信息
     *
     * @param level  行政区级别 province city district
     * @param adcode 父级行政区id
     * @return
     * @author yichenshanren
     * @date 2018/6/6
     */
    @RequestMapping("list")
    public Response getList(String level, Integer adcode) {
//        if (StringUtils.isBlank(level))
        return null;
    }


}
