package com.chouchongkeji.service.home;

import com.chouchongkeji.goexplore.common.Response;

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
}
