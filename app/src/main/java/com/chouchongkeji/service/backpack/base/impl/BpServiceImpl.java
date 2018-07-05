package com.chouchongkeji.service.backpack.base.impl;

import com.chouchongkeji.dial.dao.backpack.BpItemMapper;
import com.chouchongkeji.dial.pojo.backpack.Vbp;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.base.BpService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/7/2
 */

@Component
public class BpServiceImpl implements BpService {

    @Autowired
    private BpItemMapper bpItemMapper;

    /**
     * 背包列表
     *
     * @param userId 用户信息
     * @param type   1 物品 2 虚拟物品 3 优惠券
     * @param page
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @Override
    public Response getList(Integer userId, Integer type, PageQuery page) {
        List<Vbp> list = null;
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        switch (type) {
            case 1: // 物品
                list = bpItemMapper.selectAllByUserId(userId);
                break;
        }

        return ResponseFactory.sucData(list);
    }
}
