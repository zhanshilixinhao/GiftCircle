package com.chouchongkeji.mvc.controller.user.info;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.user.info.DistrictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yichenshanren
 * @date 2018/6/6
 */

@RestController
@RequestMapping("auth/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    /**
     * 获取行政区信息
     *
     * @param level  行政区级别 province city district
     * @param pAdcode 父级行政区id
     * @return
     * @author yichenshanren
     * @date 2018/6/6
     */
    @RequestMapping("list")
    public Response getList(String level, Integer pAdcode) {
        if (StringUtils.isBlank(level)){
            level = null;
        }
        if (pAdcode == null){
            pAdcode=0;
        }
        return districtService.getList(level,pAdcode);
    }


}
