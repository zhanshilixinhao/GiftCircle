package com.chouchongkeji.service.backpack.gift.impl;

import com.chouchongkeji.dial.dao.backpack.gift.GiftRecordMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.gift.GiftRecordService;
import com.chouchongkeji.service.backpack.gift.vo.GiftRecordItemVo;
import com.chouchongkeji.service.backpack.gift.vo.GiftRecordVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Response getInOut(Integer userId) {
        return ResponseFactory.sucData(giftRecordMapper.selectInExByUserId(userId, null, null, null));
    }

    /**
     * 获取人情账簿列表
     *
     * @param userId   用户信息
     * @param starting 开始日期
     * @param ending   结束日期
     * @param obType   类型 个人 | 家庭
     * @param page
     * @return
     * @author yichenshanren
     * @date 2018/7/9
     */
    @Override
    public Response getList(Integer userId, Long starting, Long ending, String obType, PageQuery page) {
        GiftRecordVo vo = new GiftRecordVo();
        vo.setInout(giftRecordMapper.selectInExByUserId(userId, starting, ending, obType));
        // 查询列表
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<GiftRecordItemVo> records = giftRecordMapper.selectRecordByUserId(userId, starting, ending, obType);
        vo.setRecords(records);
        return ResponseFactory.sucData(vo);
    }
}
