package com.chouchongkeji.service.backpack.gift.impl;

import com.chouchongkeji.dial.dao.backpack.gift.GiftRecordMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.gift.GiftRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 人情账簿
 *
 * @author yichenshanren
 * @date 2018/7/9
 */

@Service
public class GiftRecordServiceImpl implements GiftRecordService {

    @Autowired
    private GiftRecordMapper giftRecordMapper;

    /**
     * 获取一个用户的总的收支
     *
     * @param userId 用户信息
     * @return
     * @author yichenshanren
     * @date 2018/7/9
     */
    @Override
    public Response getInEx(Integer userId) {
        return ResponseFactory.sucData(giftRecordMapper.selectInExByUserId(userId));
    }
}
