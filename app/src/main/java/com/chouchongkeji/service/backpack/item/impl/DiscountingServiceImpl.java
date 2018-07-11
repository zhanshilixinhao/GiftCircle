package com.chouchongkeji.service.backpack.item.impl;

import com.chouchongkeji.dial.dao.backpack.BpItemMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.backpack.item.DiscountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linqin
 * @date 2018/7/11
 */
@Service
public class DiscountingServiceImpl implements DiscountingService {

    @Autowired
    private BpItemMapper bpItemMapper;

    /**
     * 背包物品折现
     *
     * @param userId 用户id
     * @param bpId   背包id
     * @return
     * @author linqin
     * @date 2018/7/11
     */
    @Override
    public Response discountRecord(Integer userId, Integer bpId) {
//        BpItem
        return null;
    }
}
