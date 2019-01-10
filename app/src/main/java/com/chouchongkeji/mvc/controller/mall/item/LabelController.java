package com.chouchongkeji.mvc.controller.mall.item;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.mall.item.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2019/1/10 16:13
 */
@RestController
@RequestMapping("noauth/v1/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 商城标签列表
     *
     * @return
     * @author linqin
     * @date 2019/1/10 16:13
     */
    @PostMapping("list")
    public Response getLabelList() {
        return labelService.getLabelList();
    }


    /**
     * 获取标签商城列表
     *
     * @param id 标签id
     * @param pageQuery
     * @return
     * @author linqin
     * @date 2019/1/10 16:13
     */
    @PostMapping("item_list")
    public Response getLabelItemList(Integer id, PageQuery pageQuery) {
        if (id == null){
            return ResponseFactory.errMissingParameter();
        }
        return labelService.getLabelItemList(id,pageQuery);
    }

}
