package com.chouchongkeji.service.mall.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

/**
 * @author linqin
 * @date 2019/1/10 16:14
 */

public interface LabelService {

    /**
     * 商城标签列表
     *
     * @return
     * @author linqin
     * @date 2019/1/10 16:13
     */
    Response getLabelList();

    /**
     * 获取标签商城列表
     *
     * @param id 标签id
     * @param pageQuery
     * @return
     * @author linqin
     * @date 2019/1/10 16:13
     */
    Response getLabelItemList(Integer id, PageQuery pageQuery);
}
