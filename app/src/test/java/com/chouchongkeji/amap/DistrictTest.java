package com.chouchongkeji.amap;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.App;
import com.chouchongkeji.dial.dao.user.DistrictMapper;
import com.chouchongkeji.dial.pojo.user.District;
import com.chouchongkeji.service.amap.AMapApiImpl;
import com.chouchongkeji.service.amap.DistrictResponse;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author yichenshanren
 * @date 2018/6/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class DistrictTest {

    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void amapTest() throws IOException {
        DistrictResponse response = AMapApiImpl.createWebApi().getDistricts("100000", 3);

        insert(response.getDistricts().get(0));


    }

    private void insert(DistrictResponse.District district) {
        if (district != null && CollectionUtils.isNotEmpty(district.getDistricts())) {
            for (DistrictResponse.District item : district.getDistricts()) {
                District ob = new District();
                ob.setpAdcode(district.getAdcode());
                ob.setAdcode(item.getAdcode());
                ob.setName(item.getName());
                ob.setLevel(item.getLevel());
                System.out.println(JSON.toJSONString(item));
                districtMapper.insert(ob);
                insert(item);
            }
        }
    }


}
