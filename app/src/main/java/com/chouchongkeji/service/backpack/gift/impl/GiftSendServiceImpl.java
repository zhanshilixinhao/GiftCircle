package com.chouchongkeji.service.backpack.gift.impl;

import com.chouchongkeji.dial.dao.backpack.BpItemMapper;
import com.chouchongkeji.dial.dao.backpack.gift.GiftRecordDetailMapper;
import com.chouchongkeji.dial.dao.backpack.gift.GiftRecordMapper;
import com.chouchongkeji.dial.pojo.backpack.BpItem;
import com.chouchongkeji.dial.pojo.backpack.gift.GiftRecordDetail;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.gift.GiftSendService;
import com.chouchongkeji.service.backpack.gift.vo.GiftItemVo;
import com.chouchongkeji.service.backpack.gift.vo.GiftRecordDetailVo;
import com.chouchongkeji.service.backpack.gift.vo.GiftSendListVo;
import com.chouchongkeji.util.Constants;
import org.apache.commons.collections.CollectionUtils;
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

    @Autowired
    private GiftRecordDetailMapper giftRecordDetailMapper;

    @Autowired
    private BpItemMapper bpItemMapper;

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
        List<GiftSendListVo> giftSendListVos = giftRecordMapper.selectListByFlagUserId(userId, flag);
        return ResponseFactory.sucData(giftSendListVos);
    }


    /**
     * 取消礼物赠送
     *
     * @param userId
     * @param recordId 礼物记录id
     * @return
     * @author linqin
     * * @date 2019/1/4 11:46
     */
    @Override
    public Response cancelSend(Integer userId, Integer recordId) {
        // 根据礼物赠送记录id查询赠送详情
        List<GiftRecordDetailVo> giftRecordDetails = giftRecordDetailMapper.selectDetailByRecordId(recordId);
        if (CollectionUtils.isEmpty(giftRecordDetails)) {
            return ResponseFactory.err("该礼物赠送记录不存在");
        }
        for (GiftRecordDetailVo giftRecord : giftRecordDetails) {
            if (giftRecord.getStatus() != Constants.GIFT_STATUS.WAIT) {
                return ResponseFactory.err("该礼物赠已被好友领取，无法撤回");
            }
            // 物品返回背包
            for (GiftItemVo giftItemVo : giftRecord.getContent()) {
                // 根据背包id查询背包物品
                BpItem bpItem = bpItemMapper.selectByPrimaryKey(giftItemVo.getBpId());
                if (bpItem == null) {
                    return ResponseFactory.err("背包中不存在该物品");
                }
                bpItem.setQuantity(bpItem.getQuantity() + 1);
                int i = bpItemMapper.updateByPrimaryKeySelective(bpItem);
                if (i < 1) {
                    return ResponseFactory.err("物品返回背包失败");
                }
            }
            // 改变礼物赠送记录详情状态
            int i = giftRecordDetailMapper.updateStatusById(giftRecord.getId(), Constants.GIFT_STATUS.CANCEL);
            if (i < 1) {
                return ResponseFactory.err("改变礼物赠送记录详情状态失败");
            }
        }
        // 改变礼物赠送记录状态
        int count = giftRecordMapper.updateStatusById(recordId, Constants.GIFT_STATUS.CANCEL);
        if (count < 1) {
            return ResponseFactory.err("改变礼物赠送记录状态失败");
        }
        return ResponseFactory.sucMsg("撤回礼物赠送成功");
    }


}
