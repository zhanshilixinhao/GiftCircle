package com.chouchongkeji.service.backpack.gift.impl;

import com.chouchongkeji.dial.dao.backpack.gift.GiftRecordMapper;
import com.chouchongkeji.dial.dao.backpack.gift.GiftRecordSelfMapper;
import com.chouchongkeji.dial.pojo.home.GiftRecordSelf;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.gift.GiftRecordService;
import com.chouchongkeji.service.backpack.gift.vo.GiftFriendVo;
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

    @Autowired
    private GiftRecordSelfMapper giftRecordSelfMapper;

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
     * @date 2018/7/9，
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

    /**
     * 礼品补录
     *
     * @param userId 用户信息
     * @param record 礼物记录
     * @return
     * @author yichenshanren
     * @date 2018/7/9
     */
    @Override
    public Response addRecord(Integer userId, GiftRecordSelf record) {
        record.setId(null);
        record.setUserId(userId);
        int insert = giftRecordSelfMapper.insert(record);
        if (insert > 0) {
            return ResponseFactory.sucMsg("添加成功!Q");
        }
        return ResponseFactory.err("添加失败!");
    }

    /**
     * 人情往来记录
     *
     * @param userId       用户信息
     * @param friendUserId 查看的好友用户id
     * @return
     * @author yichenshanren
     * @date 2018/7/9
     */
    @Override
    public Response getRecordForFriend(Integer userId, Integer friendUserId, PageQuery page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<GiftFriendVo> list = giftRecordMapper.selectByFriendUserId(userId, friendUserId);
        return ResponseFactory.sucData(list);
    }
}
