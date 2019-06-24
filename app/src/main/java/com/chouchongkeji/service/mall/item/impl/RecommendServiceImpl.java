package com.chouchongkeji.service.mall.item.impl;

import com.chouchongkeji.dial.dao.gift.item.ItemMapper;
import com.chouchongkeji.dial.dao.gift.item.RecommendedTodayMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.mall.item.RecommendService;
import com.chouchongkeji.service.mall.item.vo.ItemListVo2;
import com.chouchongkeji.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author linqin
 * @date 2019/6/24
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private RecommendedTodayMapper recommendedTodayMapper;


    /**
     * 今日推荐
     *
     * @return
     * @author linqin
     * @date 2019/6/24
     */
    @Override
    public Response getRecommendItem() throws ParseException {
        Long time = TimeUtils.time(System.currentTimeMillis());
        Long endTime = TimeUtils.timeEnd(System.currentTimeMillis());
        List<ItemListVo2> vo2s = recommendedTodayMapper.selectByTime(time,endTime);
        return ResponseFactory.sucData(vo2s);
    }

//1561305600 1561392000
}
