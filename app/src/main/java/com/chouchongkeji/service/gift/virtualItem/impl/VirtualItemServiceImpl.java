package com.chouchongkeji.service.gift.virtualItem.impl;

import com.chouchongkeji.dao.gift.virtualItem.VirtualItemCateMapper;
import com.chouchongkeji.dao.gift.virtualItem.VirtualItemMapper;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.gift.virtualItem.VirtualItemService;
import com.chouchongkeji.service.gift.virtualItem.vo.VirtualItemCateVo;
import com.chouchongkeji.service.gift.virtualItem.vo.VirtualItemVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yy
 * @date 2018/6/11
 **/

@Service
public class VirtualItemServiceImpl implements VirtualItemService {
    @Autowired
    private VirtualItemCateMapper virtualItemCateMapper;

    @Autowired
    private VirtualItemMapper virtualItemMapper;

    /**
     * 获得虚拟商品分类列表
     *
     * @param: [userId 用户id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    @Override
    public Response getVirItemCategoryList() {
        Integer status = 1;
        List<VirtualItemCateVo> virtualItemCates = virtualItemCateMapper.selectByStatus(status);
        return ResponseFactory.sucData(virtualItemCates);
    }

    /**
     * 获得商品列表
     *
     * @param: [userId 用户id, id 虚拟商品分类id, page 分页信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    @Override
    public Response getVirItemList(Integer id, PageQuery page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Integer status = 1;
        List<VirtualItemVo> virtualItemVos = virtualItemMapper.selectByCateId(id, status);
        return ResponseFactory.sucData(virtualItemVos);
    }
}
