package com.chouchongkeji.service.home;

import com.chouchongkeji.goexplore.common.Response;

import java.text.ParseException;

/**
 * @author linqin
 * @date 2018/7/6
 */
public interface HomeService {
    /**
     * 首页Banner
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    Response getItemList();

    /**
     * 首页寄售台新上架商品
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    Response getConItem();

    /**
     * 首页节日列表
     *
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    Response getFestivalList();
    /**
     * 获取整点福利
     *
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    Response getWelfare() ;



    /**
     * 首页黄历
     *
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    Response getCalendar();
}
