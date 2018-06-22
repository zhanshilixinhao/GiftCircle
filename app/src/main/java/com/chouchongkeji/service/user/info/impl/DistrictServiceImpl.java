package com.chouchongkeji.service.user.info.impl;

import com.chouchongkeji.dial.dao.user.DistrictMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.dial.pojo.user.District;
import com.chouchongkeji.service.user.info.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linqin
 * @date 2018/6/11
 */
@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    /**
     * 获取行政区信息
     *
     * @param level  行政区级别 province city district
     * @param pAdcode 父级行政区id
     * @return
     * @author linqin
     * @date 2018/6/11
     */
    @Override
    public Response getList(String level, Integer pAdcode) {
        //根据pAdcode和level查询
        List<District> district = districtMapper.selectByLevelPAdcode(level,pAdcode);
        return ResponseFactory.sucData(district);
    }
}
