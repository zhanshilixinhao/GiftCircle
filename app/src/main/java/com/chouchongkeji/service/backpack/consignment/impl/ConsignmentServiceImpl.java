package com.chouchongkeji.service.backpack.consignment.impl;

import com.chouchongkeji.dial.dao.backpack.item.BpItemMapper;
import com.chouchongkeji.dial.pojo.backpack.item.BpItem;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.backpack.consignment.ConsignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linqin
 * @date 2018/7/2
 */
@Service
public class ConsignmentServiceImpl implements ConsignmentService {

    @Autowired
    private BpItemMapper bpItemMapper;

    /**
     * 上架寄售台之前商品信息查询
     *
     * @param userId  用户Id
     * @param bpId    背包id
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    @Override
    public Response getInfo(Integer userId, Integer bpId) {
        //根据用户Id和背包id查询物品信息
        BpItem bpItem = bpItemMapper.selectByUserIdAndBpItemId(userId,bpId);
        return null;
    }
}
