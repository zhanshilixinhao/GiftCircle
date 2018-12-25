package com.chouchongkeji.service.user.info.impl;

import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.dial.dao.user.DistrictMapper;
import com.chouchongkeji.dial.redis.CacheCallback;
import com.chouchongkeji.dial.redis.MRedisTemplate;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.dial.pojo.user.District;
import com.chouchongkeji.service.user.info.DistrictService;
import com.chouchongkeji.service.user.info.vo.DistrictVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author linqin
 * @date 2018/6/11
 */
@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private MRedisTemplate mRedisTemplate;

    /**
     * 获取行政区信息
     *
     * @param level   行政区级别 province city district
     * @param pAdcode 父级行政区id
     * @return
     * @author linqin
     * @date 2018/6/11
     */
    @Override
    public Response getList(String level, Integer pAdcode) {
        //根据pAdcode和level查询
        List<District> district = districtMapper.selectByLevelPAdcode(level, pAdcode);
        return ResponseFactory.sucData(district);
    }

    /**
     * 获取行政区列表
     *
     * @return
     * @author yichenshanren
     * @date 2018/6/6
     */
    @Override
    public Response getDistrictList() {
        List<DistrictVo> list = mRedisTemplate.get(
                "gc-di-li",
                300, TimeUnit.DAYS,
                new TypeReference<List<DistrictVo>>(){},
                this::getAllDistrict);
        return ResponseFactory.sucData(list);
    }

    private List<DistrictVo> getAllDistrict() {
        List<DistrictVo> province = districtMapper.selectSimple("province", 0);
        for (DistrictVo districtVo : province) {
            List<DistrictVo> list = districtMapper.selectSimple("city", districtVo.id);
            districtVo.children = list;
            for (DistrictVo vo : list) {
                vo.children = districtMapper.selectSimple("district", vo.id);
            }
        }
        return province;
    }
}
