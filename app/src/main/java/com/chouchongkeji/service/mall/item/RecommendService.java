package com.chouchongkeji.service.mall.item;

import com.chouchongkeji.goexplore.common.Response;

import java.text.ParseException;

/**
 * @author linqin
 * @date 2019/6/24
 */

public interface RecommendService {

    /**
     * 今日推荐
     *
     * @return
     * @author linqin
     * @date 2019/6/24
     */
    Response getRecommendItem() throws ParseException;
}
