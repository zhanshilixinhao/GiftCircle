package com.chouchongkeji.service.backpack.gift.impl;

import com.chouchongkeji.dial.dao.backpack.gift.GiftRecordMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.gift.GiftSendService;
import com.chouchongkeji.service.backpack.gift.vo.GiftItemVo;
import com.chouchongkeji.service.backpack.gift.vo.GiftSendListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author linqin
 * @date 2019/1/4 11:50
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class GiftSendServiceImpl implements GiftSendService {

    @Autowired
    private GiftRecordMapper giftRecordMapper;

    /**
     * 赠送礼物列表
     *
     * @param userId
     * @param flag   1 赠送记录 2 收礼记录
     * @return
     * @author linqin
     * @date 2019/1/4 11:46
     */
    @Override
    public Response sendList(Integer userId, Byte flag) {
        // 查询赠送记录和收礼记录
        List<GiftSendListVo> giftSendListVos = giftRecordMapper.selectListByFlagUserId(userId,flag);
        return ResponseFactory.sucData(giftSendListVos);
    }
}
